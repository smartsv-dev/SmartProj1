/*
 *
 *
 * Copyright (c) 2007, 2010 NTT DATA Corporation
 *
 */
package jp.co.smartservice.fw.validator;

import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;

/**
 * ��r���Ɣ�r�Ώۂ���v���邩���肷��J�X�^���o���f�[�^�B<br>
 * <br>
 * �E���ʏ���CP0003�F��v����
 * 
 * 
 * 
 */
public class MatchValidator implements MultiFieldValidator {

    /**
     * �^����ꂽ��r���Ɣ�r�Ώۂ��r���A��v���邩�`�F�b�N����B
     * 
     * @param value
     *            ��r��
     * @param fields
     *            <br>
     *            [0]: ��r�Ώ�
     * @return ��v����: true ��v���Ȃ�: false
     */
    public boolean validate(String value, String[] fields) {

        // �������r���s�Ȃ��B
        return value.equals(fields[0]);
    }

}
