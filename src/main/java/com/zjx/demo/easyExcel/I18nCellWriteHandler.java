package com.zjx.demo.easyExcel;

import com.alibaba.excel.metadata.CellData;
import com.alibaba.excel.metadata.Head;
import com.alibaba.excel.write.handler.CellWriteHandler;
import com.alibaba.excel.write.metadata.holder.WriteSheetHolder;
import com.alibaba.excel.write.metadata.holder.WriteTableHolder;
import org.apache.commons.collections.CollectionUtils;
import org.apache.poi.ss.usermodel.Cell;
import org.apache.poi.ss.usermodel.Row;

import java.util.List;
import java.util.stream.Collectors;

/**
 * @author catalina
 * @date 2020/8/31
 */
public class I18nCellWriteHandler implements CellWriteHandler {

    @Override
    public void beforeCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Row row,
                                 Head head, Integer columnIndex, Integer relativeRowIndex, Boolean isHead) {
        if (isHead) {
            List<String> originHeadNames = head.getHeadNameList();
            if (CollectionUtils.isNotEmpty(originHeadNames)) {
                List<String> newHeadNames = originHeadNames.stream().map(headName -> PlaceholderResolver
                        .getDefaultResolver().resolveByRule(headName, (name) -> MessageHolder.get().getMessage(name, null)))
                        .collect(Collectors.toList());
                head.setHeadNameList(newHeadNames);
            }
        }
    }

    @Override
    public void afterCellCreate(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, Cell cell,
                                Head head, Integer relativeRowIndex, Boolean isHead) {

    }

    @Override
    public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder, CellData cellData, Cell cell, Head head, Integer integer, Boolean aBoolean) {

    }

    // @Override
    // public void afterCellDataConverted(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
    // CellData cellData, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {
    //
    // }

    @Override
    public void afterCellDispose(WriteSheetHolder writeSheetHolder, WriteTableHolder writeTableHolder,
                                 List<CellData> cellDataList, Cell cell, Head head, Integer relativeRowIndex, Boolean isHead) {

    }
}