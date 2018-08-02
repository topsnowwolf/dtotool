package com.snowwolf.dtotool.controller;

import com.snowwolf.dtotool.dto.JsonReqDto;
import com.snowwolf.dtotool.dto.MockDto;
import com.snowwolf.dtotool.dto.MockReqDto;
import com.snowwolf.dtotool.dto.TableReq;
import com.snowwolf.dtotool.service.IColumnService;
import com.snowwolf.dtotool.tool.BeanUtil;
import com.snowwolf.dtotool.view.ColumInfoView;
import com.snowwolf.dtotool.view.ColumView;
import com.snowwolf.dtotool.view.DataVo;
import com.snowwolf.dtotool.view.JsonView;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.BeanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;

/**
 * @author: topsnowwolf
 * @description:
 * @date: Create in 2018/7/27 20:10
 * @modified by:
 * @versions：0.1.0
 */
@Slf4j
@RestController
@RequestMapping("/json")
public class JsonToBeanController {
    @Autowired
    private IColumnService columnService;

    @PostMapping("/change")
    public DataVo changeJson(@RequestBody JsonReqDto JsonReqDto){
        TableReq tableReq = new TableReq();
        BeanUtils.copyProperties(JsonReqDto,tableReq);
        List<ColumInfoView> columInfoViews = new ArrayList<>();
        ColumView columView = columnService.findTableByTB(tableReq);
        columView.getList().forEach(columInfoView -> {
            JsonReqDto.getColums().forEach(s -> {
                if((s.toUpperCase()).equals(columInfoView.getColumnName().toUpperCase())){
                    columInfoViews.add(columInfoView);
                }
            });
        });
        List<JsonView> list = BeanUtil.tableToJson(columInfoViews,JsonReqDto.getType());
        return new DataVo("0000","SUCCESS",list);
    }
}
