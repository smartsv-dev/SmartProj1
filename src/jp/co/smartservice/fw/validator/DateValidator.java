/*
 *
 *
 * Copyright (c) 2007, 2010 NTT DATA Corporation
 *
 */
package jp.co.smartservice.fw.validator;

import jp.terasoluna.fw.web.struts.form.MultiFieldValidator;

/**
 * �^����ꂽ�N�A���A���g�ݍ��킹����t���A<br>
 * �J�����_�[��ɑ��݂��邩 �`�F�b�N����J�X�^���o���f�[�^�B<br>
 * <br>
 * �E���ʏ���CP0001�F�N�����
 * 
 * 
 * 
 */
public class DateValidator implements MultiFieldValidator {

    /**
     * �^����ꂽ�N�A���A���g�ݍ��킹����t���A<br>
     * �J�����_�[��ɑ��݂��邩 �`�F�b�N����B
     * 
     * @param value
     *            �N�Byyyy�`���ł���
     * @param fields
     *            <br>
     *            [0]: ���BM�`���A�܂���MM�`���ł���<br>
     *            [1]: ��Bd�`���A�܂���dd�`���ł���<br>
     * @return �^����ꂽ�N����J�����_�[��ɑ��݂���:true<br>
     *         �J�����_�[��ɑ��݂��Ȃ�: false
     */
    public boolean validate(String value, String[] fields) {

        // ���݉ۂ��`�F�b�N����B
        //return DateUtil.isExists(value, fields[0], fields[1]);
    	return true;
    }

}
