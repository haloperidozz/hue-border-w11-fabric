package com.github.haloperidozz.hueborder.win32.color;

public final class ColorRefEffect {
    private ColorRefEffect() {
    }

    public static ColorRef hueRotate(ColorRef colorRef, float degrees) {
        float cosValue = (float) Math.cos(Math.toRadians(degrees));
        float sinValue = (float) Math.sin(Math.toRadians(degrees));

        return applyMatrix(colorRef, new float[]{
                0.213f + cosValue * 0.787f - sinValue * 0.213f,
                0.715f - cosValue * 0.715f - sinValue * 0.715f,
                0.072f - cosValue * 0.072f + sinValue * 0.928f,

                0.213f - cosValue * 0.213f + sinValue * 0.143f,
                0.715f + cosValue * 0.285f + sinValue * 0.140f,
                0.072f - cosValue * 0.072f - sinValue * 0.283f,

                0.213f - cosValue * 0.213f - sinValue * 0.787f,
                0.715f - cosValue * 0.715f + sinValue * 0.715f,
                0.072f + cosValue * 0.928f + sinValue * 0.072f,
        });
    }

    public static ColorRef invert(ColorRef colorRef) {
        float[] norm = colorRef.normalize();

        return new ColorRef(1.0f - norm[0], 1.0f - norm[1], 1.0f - norm[2]);
    }

    private static ColorRef applyMatrix(ColorRef colorRef, float[] mat) {
        if (mat == null || mat.length != 9) {
            throw new IllegalArgumentException("matrix must have 9 elements (3x3)");
        }

        float[] norm = colorRef.normalize();

        return new ColorRef(
                norm[0] * mat[0] + norm[1] * mat[1] + norm[2] * mat[2],
                norm[0] * mat[3] + norm[1] * mat[4] + norm[2] * mat[5],
                norm[0] * mat[6] + norm[1] * mat[7] + norm[2] * mat[8]
        );
    }
}
