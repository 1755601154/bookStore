package com.example.bookStore.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (PricingRefValue)表实体类
 *
 * @author yuanlei
 * @since 2020-11-24 17:57:56
 */
@SuppressWarnings("serial")
public class PricingRefValue extends Model<PricingRefValue> {
    /*参考值定义的标识*/
    private Integer refValueId;
    /*参数值名称*/
    private String refValueName;
    /*1 -常量；2 -引用定价参数；3 -引用定价参考对象；4-引用定价参考值；5- lua脚本。LOVB=PRC-C-0012。*/
    private Integer refValueType;
    /*表示该参考值计算后返回值的类型。1-整数；2 -浮点数；3 -字符串型；4 -布尔型；5 -日期型
 
 LOVB=PRC-C-0011*/
    private Integer valueType;
    /*当REF_VALUE_TYPE=1、5的时候才需要配置，表示被引用的数值串 或lua表达式
 
 当为常量型值时
 VALUE_TYPE=2 浮点数时，该字段填浮点数，如：0.15
 VALUE_TYPE=4 布尔型时，填true/false，不区分大小写
 VALUE_TYPE=5 日期时，该字段格式为YYYYMMDDhhmmss格式*/
    private String valueString;
    /*当REF_VALUE_TYPE=2 "引用参数"时，该字段填“定价参数定义”实体的主键*/
    private Integer pricingParamId;
    /*当REF_VALUE_TYPE=3 “引用定价参考对象”时，填定价参考对象实体的主键
 */
    private Integer pricingRefObjectId;
    /*当REF_VALUE_TYPE=4 ”引用定价参考值“时，填被引用的源定价参考值标识*/
    private Integer sourceRefValueId;
    /*调整运算符定义。如：+，-，*，/等。经过运算后确定最终的返回值。LOVB=PRC-C-0038。
 1	不调整
 2	+
 3	_
 4	*
 5	/
 6	mod
 7	取月份差 （）
 25	and逻辑运算符	逻辑与运算
 26	or逻辑运算符	逻辑或运算
 27	colon冒号运算符	配合ifthen运算符使用，单独使用无意义
 28	ifthen运算符 与colon运算符共同实现C语言的三目运算符 a ? b : c
 */
    private Integer adjustMethod;
    /*当ADJUST_METHOD=1时不需要配置。表示参考值计算的右运算值。（VALUE_STRING、PRICING_PARAM_ID、PRICING_REF_OBJECT_ID、SOURCE_REF_VALUE_ID都是运算左值）*/
    private Integer adjustRefValueId;
    /*当前参考值被其他参考值引用时的精度要求。精确到小数点后的位数。
 
 如：A 参考值 引用了B 参考值，在B参考值中该字段配置为2。B参考值的计算结果是要做为 A参考值的输入的，所以在B参考值返回给A的时候，需要精确到小数点后2位。
 （即使B参考值不是运算型的，只是一个常量值，如：0.029，当CALC_PRECISION=2时，取整方式为向上取整，B返回的时候会返回0.03）*/
    private Integer calcPrecision;
    /*取整方式.1 -向上取整; 2 -向下取整; 3 -四舍五入。 LOVB=PRC-C-0013*/
    private Integer calcPrecisionMethod;
    /*费率单位的精度. 为厘的倍数，1表示厘，10表示分。当前参考值作为最终结果使用时的精度要求，即被参考值表之外的实体直接引用时。如当前记录被TARIFF表关联了，那么参考值运算结果最终返回给TARIFF的时候，会按照该精度调整后再返回*/
    private Integer ratePrecision;
    /*表示批价最终算费的精度，填的是小数的位数。如2，表示精确到小数后2位。当该记录被参考值表之外的实体直接引用时，才需要配置。譬如：当该记录直接被TARIFF表引用时，TARIFF算费后最终费用得精度按照该字段的配置来控制*/
    private Integer rateProcPrecision;
    /*状态.LOVB=PRC-C-0026.*/
    private String statusCd;
    /*创建人*/
    private Long createStaff;
    /*修改人*/
    private Long updateStaff;
    /*创建时间*/
    private Date createDate;
    /*修改时间*/
    private Date updateDate;
    /*状态时间*/
    private Date statusDate;


    public Integer getRefValueId() {
        return refValueId;
    }

    public void setRefValueId(Integer refValueId) {
        this.refValueId = refValueId;
    }

    public String getRefValueName() {
        return refValueName;
    }

    public void setRefValueName(String refValueName) {
        this.refValueName = refValueName;
    }

    public Integer getRefValueType() {
        return refValueType;
    }

    public void setRefValueType(Integer refValueType) {
        this.refValueType = refValueType;
    }

    public Integer getValueType() {
        return valueType;
    }

    public void setValueType(Integer valueType) {
        this.valueType = valueType;
    }

    public String getValueString() {
        return valueString;
    }

    public void setValueString(String valueString) {
        this.valueString = valueString;
    }

    public Integer getPricingParamId() {
        return pricingParamId;
    }

    public void setPricingParamId(Integer pricingParamId) {
        this.pricingParamId = pricingParamId;
    }

    public Integer getPricingRefObjectId() {
        return pricingRefObjectId;
    }

    public void setPricingRefObjectId(Integer pricingRefObjectId) {
        this.pricingRefObjectId = pricingRefObjectId;
    }

    public Integer getSourceRefValueId() {
        return sourceRefValueId;
    }

    public void setSourceRefValueId(Integer sourceRefValueId) {
        this.sourceRefValueId = sourceRefValueId;
    }

    public Integer getAdjustMethod() {
        return adjustMethod;
    }

    public void setAdjustMethod(Integer adjustMethod) {
        this.adjustMethod = adjustMethod;
    }

    public Integer getAdjustRefValueId() {
        return adjustRefValueId;
    }

    public void setAdjustRefValueId(Integer adjustRefValueId) {
        this.adjustRefValueId = adjustRefValueId;
    }

    public Integer getCalcPrecision() {
        return calcPrecision;
    }

    public void setCalcPrecision(Integer calcPrecision) {
        this.calcPrecision = calcPrecision;
    }

    public Integer getCalcPrecisionMethod() {
        return calcPrecisionMethod;
    }

    public void setCalcPrecisionMethod(Integer calcPrecisionMethod) {
        this.calcPrecisionMethod = calcPrecisionMethod;
    }

    public Integer getRatePrecision() {
        return ratePrecision;
    }

    public void setRatePrecision(Integer ratePrecision) {
        this.ratePrecision = ratePrecision;
    }

    public Integer getRateProcPrecision() {
        return rateProcPrecision;
    }

    public void setRateProcPrecision(Integer rateProcPrecision) {
        this.rateProcPrecision = rateProcPrecision;
    }

    public String getStatusCd() {
        return statusCd;
    }

    public void setStatusCd(String statusCd) {
        this.statusCd = statusCd;
    }

    public Long getCreateStaff() {
        return createStaff;
    }

    public void setCreateStaff(Long createStaff) {
        this.createStaff = createStaff;
    }

    public Long getUpdateStaff() {
        return updateStaff;
    }

    public void setUpdateStaff(Long updateStaff) {
        this.updateStaff = updateStaff;
    }

    public Date getCreateDate() {
        return createDate;
    }

    public void setCreateDate(Date createDate) {
        this.createDate = createDate;
    }

    public Date getUpdateDate() {
        return updateDate;
    }

    public void setUpdateDate(Date updateDate) {
        this.updateDate = updateDate;
    }

    public Date getStatusDate() {
        return statusDate;
    }

    public void setStatusDate(Date statusDate) {
        this.statusDate = statusDate;
    }

    /**
     * 获取主键值
     *
     * @return 主键值
     */
    @Override
    protected Serializable pkVal() {
        return this.refValueId;
    }
    }