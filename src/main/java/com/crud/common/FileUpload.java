package com.crud.common;
import java.io.File;
import java.io.IOException;

import com.crud.dao.BoardDAO;
import com.oreilly.servlet.*;
import com.oreilly.servlet.multipart.DefaultFileRenamePolicy;
import com.crud.bean.BoardVO;
import javax.servlet.http.*;
import javax.servlet.*;

public class FileUpload {
    public BoardVO uploadPhoto(HttpServletRequest request) {
        String filename = "";
        int sizeLimit = 15 * 1024 * 1024;

        //String realPath = request.getServletContext().getRealPath("upload");
        String realPath = null;
        File dir = new File(realPath);
        if (!dir.exists()) dir.mkdirs();

        BoardVO one = null;
        MultipartRequest multpartRequest = null;

        try{
            multpartRequest = new MultipartRequest(request, realPath,  sizeLimit, "utf-8",new DefaultFileRenamePolicy());

            filename = multpartRequest.getFilesystemName("photo");

            one = new BoardVO();
            String id = multpartRequest.getParameter("id");
            if(id!=null&&!id.equals("")) one.setId(Integer.parseInt(id));
            one.setName(multpartRequest.getParameter("name"));
            one.setAddress(multpartRequest.getParameter("address"));
            one.setFoodName(multpartRequest.getParameter("foodName"));
            one.setFlavor(multpartRequest.getParameter("flavor"));
            one.setTakeOut(Integer.parseInt(multpartRequest.getParameter("takeOut")));
            one.setRequest(multpartRequest.getParameter("request"));
            one.setPrice(Integer.parseInt(multpartRequest.getParameter("price")));
            one.setCnt(Integer.parseInt(multpartRequest.getParameter("cnt")));

            if(id!=null&&!id.equals("")) {
                BoardDAO dao = new BoardDAO();
                String oldfilename = dao.getPhotoFilename(Integer.parseInt(id));
                if(filename!=null && oldfilename!=null){
                    FileUpload.deleteFile(request, oldfilename);
                } else if (filename==null && oldfilename!=null) {
                    filename = oldfilename;
                }
            }
            one.setPhoto(multpartRequest.getParameter("photo"));
        } catch (IOException e){
            e.printStackTrace();
        }

        return one;
    }

    public static void deleteFile(HttpServletRequest request, String filename) {
        //String filePath = request.getServletContext().getRealPath("upload");

        //File f = new File(filePath + "/" + filename);
        File f = null;
        if( f.exists()) f.delete();
    }
}
