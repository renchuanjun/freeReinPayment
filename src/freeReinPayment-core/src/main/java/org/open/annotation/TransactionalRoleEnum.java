package org.open.annotation;

public enum TransactionalRoleEnum {

    /**
     * Start tcc role enum.
     */
    START(1, "发起者"),

    /**
     * Consumer tcc role enum.
     */
    LOCAL(2, "本地执行"),

    /**
     * Provider tcc role enum.
     */
    PROVIDER(3, "提供者");

    private int code;

    private String desc;

    TransactionalRoleEnum(final int code, final String desc) {
        this.code = code;
        this.desc = desc;
    }

    /**
     * Gets code.
     *
     * @return the code
     */
    public int getCode() {
        return code;
    }

    /**
     * Sets code.
     *
     * @param code the code
     */
    public void setCode(final int code) {
        this.code = code;
    }

    /**
     * Gets desc.
     *
     * @return the desc
     */
    public String getDesc() {
        return desc;
    }

    /**
     * Sets desc.
     *
     * @param desc the desc
     */
    public void setDesc(final String desc) {
        this.desc = desc;
    }
}
