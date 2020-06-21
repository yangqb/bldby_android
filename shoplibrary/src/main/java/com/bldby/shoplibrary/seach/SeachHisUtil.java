package com.bldby.shoplibrary.seach;

import com.orhanobut.hawk.Hawk;

import java.util.ArrayList;
import java.util.List;

public class SeachHisUtil {
    private static volatile SeachHisUtil singleton = null;
    private final String SavaTag = "SavaTag";
    private final int SavaTagSize = 15;

    private SeachHisUtil() {
    }

    public static SeachHisUtil getInstance() {
        if (singleton == null) {
            synchronized (SeachHisUtil.class) {
                if (singleton == null) {
                    singleton = new SeachHisUtil();
                }
            }
        }
        return singleton;
    }

    /**
     * 添加历史记录
     * @param s
     */
    public void addHis(String s) {
        List<String> strings = Hawk.get(SavaTag, new ArrayList<String>());
        if (strings == null) {
            strings = new ArrayList<>();
        }
        strings.add(0, s);
        if (strings.size() > SavaTagSize) {
            strings = strings.subList(0, SavaTagSize);
        }
        Hawk.put(SavaTag, strings);
    }

    /**
     * 获取历史记录
     * @return
     */
    public ArrayList<String> getHis() {
        ArrayList<String> strings = Hawk.get(SavaTag, new ArrayList<String>());
        if (strings != null) {
            return strings;
        }
        return new ArrayList<>();
    }


    /**
     * 删除历史记录
     * @return
     */
    public void deleteHis() {
        boolean delete = Hawk.delete(SavaTag);
        return ;
    }
}
