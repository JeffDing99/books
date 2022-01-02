package com.ding.books.utils;

import cn.hutool.core.io.IoUtil;
import cn.hutool.poi.excel.ExcelUtil;
import cn.hutool.poi.excel.ExcelWriter;
import org.apache.log4j.Logger;

import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;

/**
 * @Description TODO
 * @Author 丁帅帅
 * @Date 21/12/19 01:13
 * @Version 1.0
 */
public class ExcelUtils {

    private static Logger logger = Logger.getLogger(ExcelUtils.class);

    private static List<List<Object>> lineList = new ArrayList<>();

    /**
     * excel 导出工具类
     *
     * @param response
     * @param fileName    文件名
     * @param projects    对象集合
     * @param columnNames 导出的excel中的列名
     * @param keys        对应的是对象中的字段名字
     * @throws IOException
     */
    public static void export(HttpServletResponse response, String fileName, List<?> projects, String[] columnNames, String[] keys,String title) throws IOException {

        ExcelWriter bigWriter = ExcelUtil.getWriter(true);


        for (int i = 0; i < columnNames.length; i++) {

            logger.info("数据库列名"+columnNames[i]+"列名"+keys[i]+"\n");
            bigWriter.addHeaderAlias(columnNames[i], keys[i]);
            //bigWriter.setColumnWidth(i, 20);
            bigWriter.autoSizeColumnAll();
        }

        // 合并单元格后的标题行，使用默认标题样式
        bigWriter.merge(keys.length-1, title);
         logger.info("表+====>"+projects.size());
        // 一次性写出内容，使用默认样式，强制输出标题
        bigWriter.write(projects, true);

        //response为HttpServletResponse对象
        //response.setContentType("application/vnd.ms-excel;charset=utf-8");
        response.setContentType("application/vnd.openxmlformats-officedocument.spreadsheetml.sheet;charset=utf-8");
        //test.xls是弹出下载对话框的文件名，不能为中文，中文请自行编码
        response.setHeader("Content-Disposition", "attachment;filename=" + new String((fileName + ".xlsx").getBytes(), "iso-8859-1"));
        ServletOutputStream out = null;
        try {
            out = response.getOutputStream();
            bigWriter.flush(out, true);
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            bigWriter.close();
        }
        IoUtil.close(out);

    }


    /**
     * excel导入工具类
     *
     * @param file       文件
     * @param columNames 列对应的字段名
     * @return 返回数据集合
     * @throws OperationException
     * @throws IOException

    public static List<Map<String, Object>> leading(MultipartFile file, String[] columNames) throws OperationException, IOException {
        String fileName = file.getOriginalFilename();
        // 上传文件为空
        if (StringUtils.isEmpty(fileName)) {
            //throw new OperationException(ReturnCodeEnum.OPERATION_EXCEL_ERROR, "没有导入文件");
        }
        //上传文件大小为1000条数据
        if (file.getSize() > 1024 * 1024 * 10) {
            logger.error("upload | 上传失败: 文件大小超过10M，文件大小为：{}", file.getSize());
            //throw new OperationException(ReturnCodeEnum.OPERATION_EXCEL_ERROR, "上传失败: 文件大小不能超过10M!");
        }
        // 上传文件名格式不正确
        if (fileName.lastIndexOf(".") != -1 && !".xlsx".equals(fileName.substring(fileName.lastIndexOf(".")))) {
            //throw new OperationException(ReturnCodeEnum.OPERATION_EXCEL_ERROR, "文件名格式不正确, 请使用后缀名为.XLSX的文件");
        }

        //读取数据
       // ExcelUtil.read07BySax(file.getInputStream(), 0, createRowHandler());
        //去除excel中的第一行数据
        lineList.remove(0);

        //将数据封装到list<Map>中
        List<Map<String, Object>> dataList = new ArrayList<>();
        for (int i = 0; i < lineList.size(); i++) {
            if (null != lineList.get(i)) {
                Map<String, Object> hashMap = new HashMap<>();
                for (int j = 0; j < columNames.length; j++) {
                    Object property = lineList.get(i).get(j);
                    hashMap.put(columNames[j], property);
                }
                dataList.add(hashMap);
            } else {
                break;
            }
        }
        return dataList;
    }
     */

}
