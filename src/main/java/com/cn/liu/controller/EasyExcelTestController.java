package com.cn.liu.controller;

import com.alibaba.excel.EasyExcel;
import com.alibaba.excel.ExcelWriter;
import com.alibaba.excel.write.builder.ExcelWriterBuilder;
import com.alibaba.excel.write.builder.ExcelWriterSheetBuilder;
import com.alibaba.excel.write.metadata.WriteSheet;
import com.alibaba.excel.write.metadata.style.WriteCellStyle;
import com.alibaba.excel.write.metadata.style.WriteFont;
import com.alibaba.excel.write.style.HorizontalCellStyleStrategy;
import com.cn.liu.entity.User;
import com.cn.liu.mapper.UserMapper;
import com.cn.liu.util.easyexcel.DemoDataListener;
import org.apache.poi.ss.usermodel.FillPatternType;
import org.apache.poi.ss.usermodel.HorizontalAlignment;
import org.apache.poi.ss.usermodel.IndexedColors;
import org.apache.poi.ss.usermodel.VerticalAlignment;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.ArrayList;
import java.util.List;

/**
 * @author liu
 */
@RestController
public class EasyExcelTestController {
    @Resource
    UserMapper userMapper;

    /**
     * 从request中获取json数据
     *
     * @param request http请求
     * @return 返回
     */
    public static String getJsonRequest(HttpServletRequest request) {
        StringBuilder sb = new StringBuilder();
        String line;
        try {
            BufferedReader reader = new BufferedReader(new InputStreamReader(request.getInputStream(), StandardCharsets.UTF_8));
            while ((line = reader.readLine()) != null) {
                sb.append(line);
            }
            return sb.toString();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        } finally {
            try {
                request.getInputStream().close();
            } catch (IOException e) {
                System.out.println("输入流关闭异常");
            }
        }
    }


    @RequestMapping("/import")
    public void excelImport(){
        String fileName = "D:\\project\\地区列表.xlsx";
        EasyExcel.read(fileName, User.class, new DemoDataListener()).sheet().doRead();
    }




    @RequestMapping(value = "/export")
    public void exportExcel(HttpServletRequest httpServletRequest, HttpServletResponse response) throws IOException {
        //获取数据
        List<User> userList = userMapper.queryForList();
        List<User> userList2 = new ArrayList<>();
        User user1 = new User(1,"liu1",22,"m","1");
        User user2 = new User(2,"liu2",22,"m","2");
        User user3 = new User(3,"liu3",22,"m","3");
        User user4 = new User(4,"liu4",22,"m","4");
        User user5 = new User(5,"liu5",22,"m","5");
        userList2.add(user1);
        userList2.add(user2);
        userList2.add(user3);
        userList2.add(user4);
        userList2.add(user5);
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
        contentWriteCellStyle.setFillPatternType(FillPatternType.SQUARES);
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
        ExcelWriter excelWriter = writeWork.build();
        try {
            WriteSheet writeSheet = new WriteSheet();
            WriteSheet writeSheet2 = new WriteSheet();
            writeSheet.setSheetName("target");
            writeSheet2.setSheetName("target2");
            excelWriter.write(userList, writeSheet);
            excelWriter.write(userList2, writeSheet2);
        }
        finally {
            excelWriter.finish();
        }



        ////4、创建表格
        ExcelWriterSheetBuilder sheet = writeWork.sheet("用户信息");
        ExcelWriterSheetBuilder sheet2 = writeWork.sheet("用户信息2");
        //5、调用业务层获取数据
        //6、写入数据到表格中
        sheet.doWrite(userList);
        sheet2.doWrite(userList2);
    }



}
