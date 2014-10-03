package com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.business;

import java.util.Iterator;
import java.util.List;

import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapExportDeclarationFK;
import com.kewill.kcx.component.mapping.companies.fedex.exportImportNcts.MapImportDeclarationFK;
import com.kewill.kcx.component.mapping.countries.de.aes.msg.common.DocumentV20;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDat;
import com.kewill.kcx.component.mapping.countries.de.aes21.msg.MsgExpDatPos;
import com.kewill.kcx.component.mapping.formats.kids.common.KidsMessage;

public class MappingDefaultSE extends MappingDefaultConverter {

	@Override
	public void setImportDefault(MapImportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */
		defaultImportValuesLocation(map);
	}

	@Override
	public void setExportDefault(MapExportDeclarationFK map) {
		/*
		 * Issue 113 Version 41
		 */
		defaultExportValuesTransport(map);
		defaultExportValuesLocation(map);
		/*
		 * Issue 119
		 */
		defaultExportDocument(map);

	}

	@Override
	public void setNCTSDefault(KidsMessage msg) {
		// TODO Auto-generated method stub
	}

	private void defaultExportDocument(MapExportDeclarationFK map) {
		MsgExpDat message = map.getMessage();
		DocumentV20 doc = null;

		if (message.getDocumentList() != null
				&& message.getDocumentList().size() > 0) {

			Iterator<DocumentV20> iDocs = (message.getDocumentList())
					.iterator();

			while (iDocs.hasNext()) {
				doc = (DocumentV20) iDocs.next();
				if ("N740".equals(doc.getType())) {
					iDocs.remove();
					addDocumentToItems(doc, message.getGoodsItemList());
				}
			}
		}
	}

	private void addDocumentToItems(DocumentV20 doc,
			List<MsgExpDatPos> goodsItemList) {

		for (MsgExpDatPos item : goodsItemList) {

			item.getDocumentList().add(doc);
		}
	}
}
