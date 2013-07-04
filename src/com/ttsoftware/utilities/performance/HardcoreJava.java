package com.ttsoftware.utilities.performance;

import static java.lang.System.out;

public final class HardcoreJava
{
    private static final int ITEMS = 1 << 24;
 
    private static final byte[] arrayA = new byte[ITEMS];
    private static final byte[] arrayB = new byte[ITEMS];
    private static final byte[] arrayC = new byte[ITEMS];
    private static final byte[] arrayD = new byte[ITEMS];
    private static final byte[] arrayE = new byte[ITEMS];
    private static final byte[] arrayF = new byte[ITEMS];
    private static final byte[] arrayG = new byte[ITEMS];
    private static final byte[] arrayH = new byte[ITEMS];

    public static void main(final String[] args)
    {
        for (int i = 1; i <= 3; i++)
        {
            out.println(i + " SingleLoop duration (ns) = " + runCaseOne());
            out.println(i + " SplitLoop  duration (ns) = " + runCaseTwo());
        }

        int result = arrayA[1] + arrayB[2] + arrayC[3] +
                     arrayD[4] + arrayE[5] + arrayF[6] + arrayG[7] + arrayH[8];
        out.println("result = " + result);
    }

    public static long runCaseOne()
    {
        long start = System.nanoTime();

        int i = ITEMS;
        while (--i != 0)
        {
            byte b = (byte)i;
            arrayA[i] = b;
            arrayB[i] = b;
            arrayC[i] = b;
            arrayD[i] = b;
            arrayE[i] = b;
            arrayF[i] = b;
            arrayG[i] = b;
            arrayH[i] = b;
        }

        return System.nanoTime() - start;
    }

    public static long runCaseTwo()
    {
        long start = System.nanoTime();

        int i = ITEMS;
        while (--i != 0)
        {
            byte b = (byte)i;
            arrayA[i] = b;
            arrayB[i] = b;
            arrayC[i] = b;
            arrayD[i] = b;
        }

        i = ITEMS;
        while (--i != 0)
        {
            byte b = (byte)i;
            arrayE[i] = b;
            arrayF[i] = b;
            arrayG[i] = b;
            arrayH[i] = b;
        }

        return System.nanoTime() - start;
    }
}
