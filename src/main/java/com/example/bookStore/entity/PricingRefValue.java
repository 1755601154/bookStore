package com.example.bookStore.entity;

import java.util.Date;
import com.baomidou.mybatisplus.extension.activerecord.Model;
import java.io.Serializable;

/**
 * (PricingRefValue)表实体类
 *
 * @author yuanlei
 * @since 2021-03-20 14:57:51
 */
@SuppressWarnings("serial")
public class PricingRefValue extends Model<PricingRefValue> {
    

    private Integer refValueId;
    

    private String refValueName;
    

    private Integer refValueType;
    

    private Integer valueType;
    

    private String valueString;
    

    private Integer pricingParamId;
    

    private Integer pricingRefObjectId;
    

    private Integer sourceRefValueId;
    

    private Integer adjustMethod;
    

    private Integer adjustRefValueId;
    

    private Integer calcPrecision;
    

    private Integer calcPrecisionMethod;
    

    private Integer ratePrecision;
    

    private Integer rateProcPrecision;
    

    private String statusCd;
    

    private Long createStaff;
    

    private Long updateStaff;
    

    private Date createDate;
    

    private Date updateDate;
    

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