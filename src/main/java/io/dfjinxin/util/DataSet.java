package io.dfjinxin.util;

import com.baomidou.mybatisplus.core.metadata.IPage;

import java.io.Serializable;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * 分页工具类
 *
 * @author Mark sunlightcs@gmail.com
 */
public class DataSet<T> implements Serializable {
    private static final long serialVersionUID = -4038858332094845486L;

    private List<T> objects = null;
    private int totalRecords = 0;
    private int pageIndex = 0;
    private int pageSize = 0;
    private int pageCount = 0;
    private Map<String, Object> metas = null;

    public DataSet() {

    }

    public DataSet(List<T> objects, int pageIndex, int pageSize, int totalCount) {
        this.objects = objects;
        this.totalRecords = totalCount;
        this.pageSize = pageSize;
        this.pageIndex = pageIndex;
        this.pageCount = (int) Math.ceil((double) totalCount / pageSize);
    }

    /**
     * 分页
     */
    public DataSet(IPage<T> page) {
        this.objects = page.getRecords();
        this.totalRecords = (int) page.getTotal();
        this.pageSize = (int) page.getSize();
        this.pageIndex = (int) page.getCurrent();
        this.pageCount = (int) page.getPages();
    }

    public List<T> getObjects() {
        return objects;
    }

    public void setObjects(List<T> objects) {
        this.objects = objects;
    }

    public int getTotalRecords() {
        return totalRecords;
    }

    public void setTotalRecords(int totalRecords) {
        this.totalRecords = totalRecords;
    }

    public boolean hasResults() {
        if (objects != null && objects.size() > 0)
            return true;
        return false;
    }

    public void add(String key, Object obj) {
        if (metas == null)
            metas = new HashMap<>();

        metas.put(key, obj);
    }


    public int getPageIndex() {
        return pageIndex;
    }

    public void setPageIndex(int currPage) {
        this.pageIndex = currPage;
    }

    public int getPageSize() {
        return pageSize;
    }

    public void setPageSize(int pageSize) {
        this.pageSize = pageSize;
    }

    public int getPageCount() {
        return pageCount;
    }

    public Object getMeta(String key) {
        if (metas == null)
            return null;

        return metas.get(key);
    }

}
