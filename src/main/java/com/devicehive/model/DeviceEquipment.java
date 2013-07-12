package com.devicehive.model;


import com.google.gson.annotations.SerializedName;

import javax.persistence.*;
import javax.validation.ConstraintViolation;
import javax.validation.Validator;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.io.Serializable;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "device_equipment")
@NamedQueries({
        @NamedQuery(name = "DeviceEquipment.getByCode", query = "select de from  DeviceEquipment de where de.code = :code")
})
public class DeviceEquipment  implements Serializable {

    @Id
    @GeneratedValue(strategy=GenerationType.IDENTITY)
    private Long id;

    @Column
    @NotNull(message = "code field cannot be null.")
    @Size(min = 1, max = 128, message = "Field cannot be empty. The length of code shouldn't be more than 128 symbols.")
    private String code;


    @Column
    @NotNull
    private Date timestamp;


    @SerializedName("parameters")
    @Embedded
    @AttributeOverrides({
            @AttributeOverride(name="jsonString", column=@Column(name = "parameters"))
    })
    private JsonStringWrapper parameters;

    @ManyToOne
    @JoinColumn(name = "device_id", updatable = false)
    @NotNull(message = "device field cannot be null.")
    private Device device;


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public Date getTimestamp() {
        return timestamp;
    }

    public void setTimestamp(Date timestamp) {
        this.timestamp = timestamp;
    }

    public JsonStringWrapper getParameters() {
        return parameters;
    }

    public void setParameters(JsonStringWrapper parameters) {
        this.parameters = parameters;
    }

    public Device getDevice() {
        return device;
    }

    public void setDevice(Device device) {
        this.device = device;
    }

    /**
     * Validates deviceEquipment representation. Returns set of strings which are represent constraint violations. Set
     * will be empty if no constraint violations found.
     * @param deviceEquipment
     * DeviceEquipment that should be validated
     * @param validator
     * Validator
     * @return Set of strings which are represent constraint violations
     */
    public static Set<String> validate(DeviceEquipment deviceEquipment, Validator validator) {
        Set<ConstraintViolation<DeviceEquipment>> constraintViolations = validator.validate(deviceEquipment);
        Set<String> result = new HashSet<>();
        if (constraintViolations.size()>0){
            for (ConstraintViolation<DeviceEquipment> cv : constraintViolations)
                result.add(String.format("Error! property: [%s], value: [%s], message: [%s]",
                        cv.getPropertyPath(), cv.getInvalidValue(), cv.getMessage()));
        }
        return result;

    }
}
