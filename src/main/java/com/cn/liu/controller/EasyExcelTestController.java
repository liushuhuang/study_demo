package com.cn.liu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.cn.liu.entity.Test.User;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.util.ArrayList;
import java.util.List;

@RestController
public class EasyExcelTestController {
    /**
     * 从request中获取json数据
     *
     * @param request
     * @return
     */
    public static String getJsonRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String line = null;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), "UTF-8"));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            String string = sb.toString();
            return string;
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                request.getInputStream().close();
            } catch (IOException e) {
                return null;
            }
        }
    }

    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        //获取数据
        List<User> userList = new ArrayList<>();
        User user1 = new User("liu1","1001",22,"m","1");
        User user2 = new User("liu2","1002",22,"m","2");
        User user3 = new User("liu3","1003",22,"m","3");
        User user4 = new User("liu4","1004",22,"m","4");
        User user5 = new User("liu5","1005",22,"m","5");
        userList.add(user1);
        userList.add(user2);
        userList.add(user3);
        userList.add(user4);
        userList.add(user5);
        // 头的策略
        WriteCellStyle headWriteCellStyle = new WriteCellStyle();
        // 背景设置为灰色
        headWriteCellStyle.setFillForegroundColor(IndexedColors.GREY_25_PERCENT.getIndex());
        WriteFont headWriteFont = new WriteFont();
        headWriteFont.setFontHeightInPoints((short)12);
        // 字体样式
        headWriteFont.setFontName("Frozen");
        headWriteCellStyle.setWriteFont(headWriteFont);
        //自动换行
        headWriteCellStyle.setWrapped(false);
        // 水平对齐方式
        headWriteCellStyle.setHorizontalAlignment(HorizontalAlignment.CENTER);
        // 垂直对齐方式
        headWriteCellStyle.setVerticalAlignment(VerticalAlignment.CENTER);

        // 内容的策略
        WriteCellStyle contentWriteCellStyle = new WriteCellStyle();
        // 这里需要指定 FillPatternType 为FillPatternType.SOLID_FOREGROUND 不然无法显示背景颜色.头默认了 FillPatternType所以可以不指定
//        contentWriteCellStyle.setFillPatternType(FillPatternType.SQUARES);
        // 背景白色
        contentWriteCellStyle.setFillForegroundColor(IndexedColors.WHITE.getIndex());
        WriteFont contentWriteFont = new WriteFont();
        // 字体大小
        contentWriteFont.setFontHeightInPoints((short)12);
        // 字体样式
        contentWriteFont.setFontName("Calibri");
        contentWriteCellStyle.setWriteFont(contentWriteFont);

        //attachment指定独立文件下载  不指定则回浏览器中直接打开
        //1、设定响应类型
        response.setContentType("application/vnd.ms-excel");
        //2、设定附件的打开方法为：下载，并指定文件名称为category.xlsx
        //将文件名称转码再使用
        String fileName = URLEncoder.encode("刘述煌", "UTF-8");

        response.setHeader("content-disposition","attachment;filename="+fileName+".xlsx");
        //3、、创建工作簿
        ExcelWriterBuilder writeWork = EasyExcel.write(response.getOutputStream(), User.class).registerWriteHandler(new HorizontalCellStyleStrategy(headWriteCellStyle, contentWriteCellStyle));
        //4、创建表格
        ExcelWriterSheetBuilder sheet = writeWork.sheet();
        //5、调用业务层获取数据
        //List<Category> categories = categoryService.findAll();
        //6、写入数据到表格中
        sheet.doWrite(userList);
    }
}
