package com.kewill.kcx.component.mapping.formats.kff.msg;

import java.util.List;
import java.util.Vector;

import javax.xml.stream.XMLEventReader;
import javax.xml.stream.XMLStreamException;

import org.xml.sax.Attributes;

import com.kewill.kcx.component.mapping.countries.de.aes.msg.KCXMessage;
import com.kewill.kcx.component.mapping.formats.kff.common.CargoItem;
import com.kewill.kcx.component.mapping.formats.kff.common.JobInformation;
import com.kewill.kcx.component.mapping.util.Utils;

/**
 * Modul		: PORT<br>
 * Erstellt		: 17.05.2013<br>
 * Beschreibung	: KFF Msg JOB with multiple JobInformations.
 * 
 * @author iwaniuk
 * @version 1.0.00
 */

public class MsgMultiJOB extends KCXMessage {

	private List <JobInformation> jobInformationList;
	private boolean isBL = false;
	
	public MsgMultiJOB() {
		super();		
	}

	public MsgMultiJOB(XMLEventReader parser) throws XMLStreamException {
		super(parser);		
	}		
	
	private enum EMsgMultiKff {		
		JobInformation;		
	}
	
	public void startElement(Enum tag, String value, Attributes attr) {
		String dummy;
		dummy = "test";
		if (value == null) {			
			switch ((EMsgMultiKff) tag) {					
				
				case JobInformation:				
					JobInformation job = new JobInformation(getScanner());
					job.parse(tag.name());	
					addJobInformationList(job);
					break;
						
				default:
					return;
			}
		} else {
			switch ((EMsgMultiKff) tag) {
			
			default:
				return;
			}
		}	
	}
	
	public void stoppElement(Enum tag) {
	}
		
	public Enum translate(String token) {
		try {
				return EMsgMultiKff.valueOf(token);
			} catch (IllegalArgumentException e) {
				return null;
			}
	}
	
	public List <JobInformation> getJobInformationList() {
		return jobInformationList;
	}
	public void setJobInformationList(List <JobInformation> list) {
		this.jobInformationList = list;
	}	
	public void addJobInformationList(JobInformation item) {
		if (jobInformationList == null) {
			jobInformationList = new Vector <JobInformation>();
		}
		jobInformationList.add(item);
	}
	
	public boolean isBL() {
		return isBL;
	}
	
	// Da aus KFF bei MasterBLs keine Zuordnung  Positionen / Container 
	// ausgegeben wird, müssen bei MasterBLs die Informationen aus den
	// HausBLs gelesen werden.
	// IM MsgJob gibt es:
	// entweder genau eine JobInformation mit 
	// einem Hafendatensatz  bzw. einem HausBL bzw. einem DirektBL
	// oder mehrere JobInformations, 
	// die genau einen MasterBL und mindestens einen HausBL enthält
	public MsgJOB mapMsgJob() {
		MsgJOB msgJob = new MsgJOB();
		int iMasterBL = 0;
		int i = 0;
		if (jobInformationList != null) {
			for (JobInformation ji : jobInformationList) {
				if (ji != null && !Utils.isStringEmpty(ji.getShpType())) {
					if (ji.getShpType().equalsIgnoreCase("M")) {
						iMasterBL = i;
						break;
					}
				}
				i = i + 1;
			}
			JobInformation jobInfo = null;
	
			if (jobInformationList.size() == 1) {
				jobInfo = jobInformationList.get(0);
			} else  {
				jobInfo = jobInformationList.get(iMasterBL);
			}			
			
			setMsgJob(msgJob, jobInfo);

			// Master BL mit House BLs
			// IN diesem Fall werden CargoItems des MasterBLs 
			// durch die CargoItems der HouseBLs ersetzt
			if (jobInformationList.size() > 1 && msgJob.isBL()) {
				msgJob = setMasterMsgJob(msgJob);
			}
		}
		
		return msgJob;
	}

	private MsgJOB setMasterMsgJob(MsgJOB msgJob) {
		int iCargoItem = 0;

		msgJob.setCargoItemList(null);

		//kumuliere/austausche cargoItems fuer BL
		for (JobInformation ji : jobInformationList) {
			if (ji != null && !Utils.isStringEmpty(ji.getShpType()) && !ji.getShpType().equalsIgnoreCase("M")) {
				for (CargoItem ci : ji.getCargoItemList()) {
					if (ci != null) {
						ci.setItemSNO(++iCargoItem + "");
						msgJob.addCargoItemList(ci);
					}
				}
			}
		}

		return msgJob;
	}

	private void setMsgJob(MsgJOB msgJob, JobInformation jobInfo) {
		msgJob.setBookingDate(jobInfo.getBookingDate());
		msgJob.setBookingNo(jobInfo.getBookingNo());
		msgJob.setCargoItemList(jobInfo.getCargoItemList());
		msgJob.setCargoValue(jobInfo.getCargoValue());
		msgJob.setCargoValueCurr(jobInfo.getCargoValueCurr());
		msgJob.setCarrierCode(jobInfo.getCarrierCode());
		msgJob.setContainerList(jobInfo.getContainerList());
		msgJob.setCsgnAddrList(jobInfo.getCsgnAddrList());
		msgJob.setCsgnCTRYCode(jobInfo.getCsgnCTRYCode());
		msgJob.setCsgnEmail(jobInfo.getCsgnEmail());
		msgJob.setCsgnEORINo(jobInfo.getCsgnEORINo());
		msgJob.setCsgnFax(jobInfo.getCsgnFax());
		msgJob.setCsgnName(jobInfo.getCsgnName());
		msgJob.setCsgnPartyId(jobInfo.getCsgnPartyId());
		msgJob.setCsgnPIC(jobInfo.getCsgnPIC());
		msgJob.setCsgnPostalCode(jobInfo.getCsgnPostalCode());
		msgJob.setCsgnTel(jobInfo.getCsgnTel());
		msgJob.setCustomsDeclType(jobInfo.getCustomsDeclType());
		msgJob.setDeclPlace(jobInfo.getDeclPlace());
		msgJob.setDestCity(jobInfo.getDestCity());
		msgJob.setDestCtry(jobInfo.getDestCtry());
		msgJob.setDestName(jobInfo.getDestName());
		msgJob.setDevryCity(jobInfo.getDevryCity());
		msgJob.setDevryCtry(jobInfo.getDevryCtry());
		msgJob.setDevryName(jobInfo.getDevryName());
		msgJob.setFuncCode(jobInfo.getFuncCode());
		msgJob.setItemHazardContact(jobInfo.getItemHazardContact());
		msgJob.setItemHazardContactTel(jobInfo.getItemHazardContactTel());
		msgJob.setJobDate(jobInfo.getJobDate());
		msgJob.setJobKCX(jobInfo.getJobKCX());
		msgJob.setJobNo(jobInfo.getJobNo());
		msgJob.setJobType(jobInfo.getJobType());
		msgJob.setKaiCde(jobInfo.getKaiCde());
		msgJob.setMotherVesselCallSign(jobInfo.getMotherVesselCallSign());
		msgJob.setMotherVesselCode(jobInfo.getMotherVesselCode());
		msgJob.setMotherVesselEta(jobInfo.getMotherVesselEta());
		msgJob.setMotherVesselEtd(jobInfo.getMotherVesselEtd());
		msgJob.setMotherVesselLioydsregno(jobInfo.getMotherVesselLioydsregno());
		msgJob.setMotherVesselName(jobInfo.getMotherVesselName());
		msgJob.setMotherVoyage(jobInfo.getMotherVoyage());
		msgJob.setPodCity(jobInfo.getPodCity());
		msgJob.setPodCtry(jobInfo.getPodCtry());
		msgJob.setPolCity(jobInfo.getPolCity());
		msgJob.setPolCtry(jobInfo.getPolCtry());
		msgJob.setRdrCde(jobInfo.getRdrCde());
		msgJob.setRefNoList(jobInfo.getRefNoList());
		msgJob.setRemarkList(jobInfo.getRemarkList());
		msgJob.setSea(jobInfo.getSea());                  //AK20130522
		msgJob.setShpNo(jobInfo.getShpNo());
		msgJob.setShprAddrList(jobInfo.getShprAddrList());
		msgJob.setShprBEFunction(jobInfo.getShprBEFunction());
		msgJob.setShprCtryCode(jobInfo.getShprCtryCode());
		msgJob.setShprEmail(jobInfo.getShprEmail());
		msgJob.setShprEORINo(jobInfo.getShprEORINo());
		msgJob.setShprFax(jobInfo.getShprFax());
		msgJob.setShprName(jobInfo.getShprName());
		msgJob.setShprPartyId(jobInfo.getShprPartyId());
		msgJob.setShprPIC(jobInfo.getShprPIC());
		msgJob.setShprPostalCode(jobInfo.getShprPostalCode());
		msgJob.setShprTel(jobInfo.getShprTel());
		msgJob.setSrvReqSubcName(jobInfo.getSrvReqSubcName());
		msgJob.setSrvReqTruckNo(jobInfo.getSrvReqTruckNo());
		msgJob.setTotGWGT(jobInfo.getTotGWGT());
		msgJob.setTotPCS(jobInfo.getTotPCS());
		msgJob.setTotVOL(jobInfo.getTotVOL());
		msgJob.setTypeOfPermit(jobInfo.getTypeOfPermit());
		msgJob.setUnid(jobInfo.getUnid());
	}
}
