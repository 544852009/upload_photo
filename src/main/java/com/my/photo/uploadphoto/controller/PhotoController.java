package com.my.photo.uploadphoto.controller;

import com.my.photo.uploadphoto.Exception.UserResult;
import com.my.photo.uploadphoto.dao.UserPhotoDao;
import com.my.photo.uploadphoto.entity.PhotoType;
import com.my.photo.uploadphoto.entity.UserPhoto;
import com.my.photo.uploadphoto.service.UserPhotoService;
import com.my.photo.uploadphoto.service.UserPhotoTypeService;
import com.my.photo.uploadphoto.util.UserInfoUtils;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.validation.Valid;
import java.text.SimpleDateFormat;
import java.util.*;

@Controller
@RequestMapping(value = "/photo")
public class PhotoController {

    @Autowired
    private UserPhotoService photoService;

    @Autowired
    private UserPhotoDao userPhotoDao ;


    @Autowired
    private UserPhotoTypeService photoTypeService;



    private Logger logger = LoggerFactory.getLogger(PhotoController.class);




    @GetMapping("/add")
    public String addPhoto() {
        return "add-photo";
    }


    @PostMapping("/add")
    @ResponseBody
    public UserResult addPhoto(@Valid UserPhoto userPhoto, HttpServletRequest request ,BindingResult bindingResult, Model model) {


        try {
            userPhoto.setUserName(UserInfoUtils.getCurrentUser(request).getUserName());
            photoService.savePhoto(bindingResult.getAllErrors(), userPhoto);
        } catch (Exception e) {
            return UserResult.error(e.getMessage());
        }

        return UserResult.success("添加成功！");
    }


    @PostMapping("/addLunBoPhoto")
    @ResponseBody
    public UserResult addLunBoPhoto(UserPhoto userPhoto) {

        photoService.savePhotos(userPhoto);
        return UserResult.success("添加成功！");
    }

    @GetMapping("/userPhotoList")
    public String userPhotoList(HttpServletRequest request ,Model model) {

//        List<UserPhoto> photoList = photoService.findPhotoList();
        List<UserPhoto> photoList = photoService.findPhotoListByUserName(UserInfoUtils.getCurrentUserName(request));
        model.addAttribute("photoList", photoList);


        return "index";
    }


    @GetMapping("/fuliPhotos")
    public String fuliPhotos(HttpServletRequest request ,Model model) {
        String imgType = request.getParameter("imgType");
        model.addAttribute("imgType",imgType);
        return "fuli-type";
    }

    @GetMapping("/fuliPhotosDetail")
    public String fuliPhotosDetail(HttpServletRequest request ,Model model) {
        model.addAttribute("parentId",request.getParameter("parentId"));
        return "fuli";
    }

    @GetMapping("/userPhotoLunBoList")
    public String userPhotoLunBoList(Model model) {

        List<UserPhoto> photoList = photoService.findPhotoLunBoList("10");
        model.addAttribute("photoList", photoList);


        return "index";
    }

    @GetMapping("/imgs")
    public String imgs(Model model){

        List<UserPhoto> photoList = photoService.findPhotoList();
        model.addAttribute("photoList", photoList);
        return "img-box";
    }

    @PostMapping("/deleteImg")
    @ResponseBody
    public UserResult deleteImg(HttpServletRequest request ,@RequestParam Long imgId) {

        if(!"1500790877@qq.com".equals(UserInfoUtils.getCurrentUserName(request))){
            return UserResult.error("您没有权限");
        }

        UserPhoto userPhoto = userPhotoDao.getOne(imgId);
        if(userPhoto != null){
            photoService.delete(userPhoto);
        }

        return UserResult.success("删除成功");
    }


    @PostMapping("/deleteImgType")
    @ResponseBody
    public UserResult deleteImgType(HttpServletRequest request ,@RequestParam Long parentId) {

        if(!"1500790877@qq.com".equals(UserInfoUtils.getCurrentUserName(request))){
            return UserResult.error("您没有权限");
        }

        photoTypeService.deleteAll(parentId);

        return UserResult.success("删除成功");
    }



    @PostMapping("/userPhotoPageList")
    @ResponseBody
    public UserResult userPhotoPageList(HttpServletRequest request ,@RequestParam("page") int page, @RequestParam("pageSize") int pageSize, @RequestParam("parentId") String parentId, Model model) {


        SimpleDateFormat simpleDateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");

        logger.error(UserInfoUtils.getCurrentUser(request).getUserName()+":选择了："+parentId+"<"+simpleDateFormat.format(new Date()));
        Page<UserPhoto> userPhotos = photoService.userPhotoPageList(page, pageSize,parentId);
        model.addAttribute("photoList", userPhotos.getContent());
        model.addAttribute("allPage",Math.ceil(userPhotos.getTotalElements()/pageSize)+1);
        model.addAttribute("page",page+1);
        return UserResult.success("success",model);
    }


    @PostMapping("/userPhotoTypePageList")
    @ResponseBody
    public UserResult userPhotoTypePageList(@RequestParam("page") int page, @RequestParam("pageSize") int pageSize,@RequestParam("imgType") int imgType,@RequestParam(value = "passWord",required = false) String passWord, Model model) {

        if(imgType == 400){
            if(StringUtils.isEmpty(passWord) || !passWord.equals("17795738191")){
                return UserResult.error("对不起,密码错误");
            }
        }

        Page<PhotoType> userPhotos = photoTypeService.getPhotoTypes(page, pageSize,imgType);
        model.addAttribute("photoList", userPhotos.getContent());
        model.addAttribute("allPage",Math.ceil(userPhotos.getTotalElements()/pageSize)+1);
        model.addAttribute("page",page+1);
        return UserResult.success("success",model);
    }

}
