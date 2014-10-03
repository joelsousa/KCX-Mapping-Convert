package com.kewill.kcx.component.mapping.countries.de.cmp;

public enum ECmpMessages {

	CMPCompleteDataFormat,
	FlightManifestMessage, 	FlightManifest,    //NotificationOfPresentation,
	FreightWayBill, 		WayBill, Waybill,
	FlightStatusUpdate, 	StatusMessage,
	CustomsStatusNotification,	//same
	CustomsResponse,  
	
	//Kids-Namen
	NotificationOfPresentation, 			//FSS-SUK, VSA/ESV
	NotificationOfPresentationConfirmed,	//FSS-SBK, ESA
	ReExport,							//FSS-SAK,
	NCTSDeclaration,						//FSS-VAN
	
	//Antworten:
	NCTSWriteOffNotification,	//TUFSTA, FSS-VSO, NCTS-OUT
	GoodsReleasedExternal, 		//CUSTST, FSS-EVK, IE25, Rückgabe Verwahrmitteilung aus NCTS //EI20140214: stand vorher CUSCAN
	NotificationOfCompletion ,  //CUSFIN, FSS-SEK, Mitteilung der Erledigung UIDS:NotificationOfSettlement
	GoodsReleasedInternal, 		//CUSTST, FSS-SWV, Verwahrmitteilung	
	ControlDecision, 			//CUSSTP, FSS-SST, Bekanntgabe einer Massnahme/Customs information about the instructed stop of transport 
	ProcessingResults,  		//CUSREC, FSS-SCK  Verarbeitungsergebnisse
	
	
	//alet namen fuer package cmp_xcargo == eigentlich gar nicht mehr verwendet:
	FlightManifestData,
	DeclarationData,
	HouseDeclarationData,
}
