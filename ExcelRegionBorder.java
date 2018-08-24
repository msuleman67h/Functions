/*
  Puts outside border around a region based on value of column A
  If column A has repeating value
  Assuming Column A is sorted
*/
public void createTable(XSSFSheet sheet) throws FileNotFoundException, UnsupportedEncodingException {

		int iterator = 1, lastColumnNumber = sheet.getRow(0).getLastCellNum() - 1;
		String repeatingRows = "";
		while (iterator <= sheet.getLastRowNum()) {
			String signalName = sheet.getRow(iterator).getCell(0).getStringCellValue();
			for (int i = iterator; i <= sheet.getLastRowNum(); i++) {
				if (signalName.equals(sheet.getRow(i).getCell(0).getStringCellValue())) {
					repeatingRows += i + ",";
				}
			}
			String[] rowsIndex = repeatingRows.split(",");
			iterator += rowsIndex.length;
			repeatingRows = "";

			CellRangeAddress region = new CellRangeAddress(Integer.parseInt(rowsIndex[0]),
					Integer.parseInt(rowsIndex[rowsIndex.length - 1]), 0, lastColumnNumber);
			RegionUtil.setBorderBottom(BorderStyle.THIN, region, sheet);
			RegionUtil.setBorderTop(BorderStyle.THIN, region, sheet);
			RegionUtil.setBorderLeft(BorderStyle.THIN, region, sheet);
			RegionUtil.setBorderRight(BorderStyle.THIN, region, sheet);
		}
	}
