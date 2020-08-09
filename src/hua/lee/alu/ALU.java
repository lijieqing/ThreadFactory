package hua.lee.alu;

import java.util.Arrays;

public class ALU {
    /**
     * 半加器
     *
     * @param a 待加数
     * @param b 待加数
     * @return 半加后的本位和进位
     */
    public int[] halfAdder(int a, int b) {
        int carry;
        int sum;
        sum = XOR(a, b);
        carry = AND(a, b);
        return new int[]{sum, carry};
    }

    /**
     * 全加器
     *
     * @param a 待加数
     * @param b 待加数
     * @param c 待进位数
     * @return 全加后的本位和进位
     */
    public int[] fullAdder(int a, int b, int c) {
        int carry;
        int sum;
        int[] first = halfAdder(a, b);
        int[] second = halfAdder(first[0], c);
        sum = second[0];
        carry = OR(second[1], first[1]);
        return new int[]{sum, carry};
    }

    /**
     * 异或运算
     */
    public int XOR(int a, int b) {
        return a ^ b;
    }

    /**
     * 与运算
     */
    public int AND(int a, int b) {
        return a & b;
    }

    /**
     * 或运算
     */
    public int OR(int a, int b) {
        return a | b;
    }

    /**
     * 8 位加法器，一步一个脚印版本
     *
     * @param binaryA 二进制数组
     * @param binaryB 二进制数组
     * @return 二进制
     */
    public int[] FullAdder_8_Bit(int[] binaryA, int[] binaryB) {
        int[] out = new int[8];
        int[] first = halfAdder(binaryA[7], binaryB[7]);
        out[7] = first[0];
        int[] second = fullAdder(binaryA[6], binaryB[6], first[1]);
        out[6] = second[0];
        int[] three = fullAdder(binaryA[5], binaryB[5], second[1]);
        out[5] = three[0];
        int[] four = fullAdder(binaryA[4], binaryB[4], three[1]);
        out[4] = four[0];
        int[] five = fullAdder(binaryA[3], binaryB[3], four[1]);
        out[3] = five[0];
        int[] six = fullAdder(binaryA[2], binaryB[2], five[1]);
        out[2] = six[0];
        int[] seven = fullAdder(binaryA[1], binaryB[1], six[1]);
        out[1] = seven[0];
        out[0] = seven[1];
        System.out.println(Arrays.toString(out));
        return out;
    }

    /**
     * 优化版本，自定义加法器长度
     *
     * @param binaryA 待加数组
     * @param binaryB 待加数组
     * @param bitLen  运算长度
     * @return 计算后的数组长度
     */
    public int[] FullAdderBits(int[] binaryA, int[] binaryB, int bitLen) {
        int[] out = new int[bitLen];
        int[] tmpRes;
        int[] first = halfAdder(binaryA[7], binaryB[7]);
        out[bitLen - 1] = first[0];
        tmpRes = first;
        for (int i = bitLen - 1; i > 0; i--) {
            tmpRes = fullAdder(binaryA[i], binaryB[i], tmpRes[1]);
            out[i] = tmpRes[0];
        }
        out[0] = tmpRes[1];
        System.out.println(Arrays.toString(out));
        return out;
    }

    public static void main(String[] args) {
        ALU alu = new ALU();
        //                 {高位-----------低位}
        int[] a = new int[]{0, 1, 1, 1, 0, 0, 1, 0};//114
        int[] b = new int[]{0, 0, 1, 1, 0, 0, 1, 1};//51
        alu.FullAdder_8_Bit(a, b);
        alu.FullAdderBits(a, b, 8);
    }
}