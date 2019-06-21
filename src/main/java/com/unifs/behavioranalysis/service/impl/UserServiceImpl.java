package com.unifs.behavioranalysis.service.impl;

import com.unifs.behavioranalysis.bean.AreaInfo;
import com.unifs.behavioranalysis.bean.User;
import com.unifs.behavioranalysis.dao.AreaInfoMapper;
import com.unifs.behavioranalysis.dao.UserMapper;
import com.unifs.behavioranalysis.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.UUID;

//保护注释
@Service
public class UserServiceImpl implements UserService
{

    @Autowired
    private UserMapper userMapper;
    @Autowired
    private AreaInfoMapper areaInfoMapper;

//  查询后转换地区字符(最低到市)

    @Override
    public List<User> selectchange(User user){
        List<User> ans = userMapper.selectByPrimaryKey(user);
        int i =0;
        AreaInfo tmp;
        while(i<ans.size()){
            tmp = areaInfoMapper.selectByPrimaryKey(ans.get(i).getDepartmentId());
//            如果为区、县，则修改为市
            if(tmp.getAreaId()!=tmp.getExStatus()){
                String str = tmp.getParentId();
                tmp=areaInfoMapper.selectByPrimaryKey(str);
            }
            ans.get(i).setDepartmentId(tmp.getAreaName());
            i++;
        }
        return ans;
    }




    @Override
    //    新增用户：userinsert
    public void userinsert(User user)
    {
        user.setUserId(UUID.randomUUID().toString().replace("-", ""));
        userMapper.insert(user);
    }

    //    删除用户：userdelete
    @Override
    public void userdelete(String id)
    {
        userMapper.deleteByPrimaryKey(id);
    }

    //    修改用户：userupdate
    @Override
    public void userupdate(User user)
    {
        userMapper.updateByPrimaryKey(user);
    }

    //    查询所有：userselectall
    @Override
    public List<User> userselectall()
    {
        return userMapper.selectAll();

    }

    //    查询用户：userselect
    @Override
    public List<User> userselect(User user)
    {
        return userMapper.selectByPrimaryKey(user);
    }

    @Override
    public List<HashMap<String, Object>> parseList()
    {

        List<AreaInfo> ProvinceInfos = areaInfoMapper.selectByParentId("0");       int i = 0;
        int j = ProvinceInfos.size();

        List<HashMap<String, Object>> rootList = new ArrayList<HashMap<String, Object>>();
        for (AreaInfo p :ProvinceInfos)
        {
            HashMap<String, Object> childMap = new HashMap<String, Object>();

            childMap.put("id",Integer.toString(++i));
            childMap.put("label",p.getAreaName());
            List<String> CityInfos = areaInfoMapper.selectAreaNameByParentId(p.getAreaId());
            List<HashMap<String, Object> > list = new ArrayList< HashMap<String, Object>>();
            for (String City:CityInfos)
            {
                HashMap<String, Object> map = new HashMap<String, Object>();
                map.put("id",Integer.toString(++j));
                map.put("lable",City);
                list.add(map);
            }
            childMap.put("children",list);

            rootList.add(childMap);
        }

        return rootList;


    }


}
