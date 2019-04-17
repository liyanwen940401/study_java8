package com.domin;

/**
 * <p></p>
 *
 * <PRE>
 * <BR>	修改记录
 * <BR>-----------------------------------------------
 * <BR>	修改日期			修改人			修改内容
 * </PRE>
 *
 * @author liyw4
 * @since 1.0
 * @version 1.0
 */
public class FaceAuthenGroup extends FaceAuthen{

    private String group_id;
    private String group_name;
    private String group_remark;

    public String getGroup_id() {
        return group_id;
    }

    public void setGroup_id(String group_id) {
        this.group_id = group_id;
    }

    public String getGroup_name() {
        return group_name;
    }

    public void setGroup_name(String group_name) {
        this.group_name = group_name;
    }

    public String getGroup_remark() {
        return group_remark;
    }

    public void setGroup_remark(String group_remark) {
        this.group_remark = group_remark;
    }

}
