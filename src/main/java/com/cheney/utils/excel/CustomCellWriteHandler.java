package com.cheney.utils.excel;

import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.metadata.data.CellData;
import com.alibaba.excel.metadata.data.WriteCellData;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.handler.SheetWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import com.alibaba.excel.write.metadata.holder.WriteWorkbookHolder;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.DataValidation;
import org.apache.poi.ss.usermodel.DataValidationConstraint;
import org.apache.poi.ss.usermodel.DataValidationHelper;
import org.apache.poi.ss.util.CellRangeAddressList;

import java.util.List;

/**
 * @author cheney
 * 日期 2023/7/26
 */
public class CustomCellWriteHandler implements SheetWriteHandler {

    @Override
    public void afterSheetCreate(WriteWorkbookHolder writeWorkbookHolder, WriteSheetHolder writeSheetHolder) {
        // 区间设置 第一列第一行和第二行的数据。由于第一行是头，所以第一、二行的数据实际上是第二三行
        CellRangeAddressList cellRangeAddressList = new CellRangeAddressList(1, 1048575, 0, 0);
        DataValidationHelper helper = writeSheetHolder.getSheet().getDataValidationHelper();
        DataValidationConstraint constraint = helper.createExplicitListConstraint(new String[] {"1号线", "2号线"});
        DataValidation dataValidation = helper.createValidation(constraint, cellRangeAddressList);
        writeSheetHolder.getSheet().addValidationData(dataValidation);

        CellRangeAddressList cellRangeAddressList1 = new CellRangeAddressList(1, 1048575, 1, 1);
        DataValidationConstraint constraint1 = helper.createExplicitListConstraint(new String[] {"体育西", "体育东"});
        DataValidation dataValidation1 = helper.createValidation(constraint1, cellRangeAddressList1);
        writeSheetHolder.getSheet().addValidationData(dataValidation1);
    }
}
