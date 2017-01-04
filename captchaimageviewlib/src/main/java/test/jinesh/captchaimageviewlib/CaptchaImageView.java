package test.jinesh.captchaimageviewlib;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Typeface;
import android.util.AttributeSet;
import android.widget.ImageView;

import java.util.Random;

/**
 * Created by Jinesh Francis on 24-11-2016.
 */

public class CaptchaImageView extends ImageView {
    private CaptchaGenerator.Captcha generatedCaptcha;
    private int captchaLength = 6;
    private int captchaType = CaptchaGenerator.NUMBERS;
    private int width, height;
    private boolean isDot;
    private boolean isRedraw;
    public CaptchaImageView(Context context) {
        super(context);
    }

    public CaptchaImageView(Context context, AttributeSet attrs) {
        super(context, attrs);

    }

    private void draw(int width, int height) {
        generatedCaptcha = CaptchaGenerator.regenerate(width, height, captchaLength, captchaType, isDot);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        width = getMeasuredWidth();
        height = getMeasuredHeight();
    }

    /**
     * Returns the generated captcha bitmap image
     *
     * @return Generated Bitmap
     */
    public Bitmap getCaptchaBitmap() {
        return generatedCaptcha.getBitmap();
    }

    /**
     * Returns the generated captcha code
     *
     * @return captcha string
     */
    public String getCaptchaCode() {
        return generatedCaptcha.getCaptchaCode();
    }

    /**
     * Regenerates the captcha again to result a new bitmap and captcha code
     */
    public void regenerate() {
        reDraw();
    }

    /**
     * Sets the type of captcha need to generate.Default value is CaptchaGenerator.NUMBER
     *
     * @param type Type of the captcha
     *             CaptchaGenerator.NUMBER - Generates a captcha with only numbers
     *             CaptchaGenerator.ALPHABETS - Generates a captcha with only numbers
     *             CaptchaGenerator.BOTH - Generates a captcha with both numbers and letter
     */
    public void setCaptchaType(int type) {
        captchaType = type;
    }

    /**
     * Sets the desired length of captcha need to generate
     *
     * @param length length of captcha
     */
    public void setCaptchaLength(int length) {
        captchaLength = length;
    }

    /**
     * Redraws the captcha
     */
    private void reDraw() {
        draw(width, height);
        setImageBitmap(generatedCaptcha.getBitmap());
    }

    /**
     * Method to set Background dots
     *
     * @param isNeeded pass true if dots needed false otherwise
     */
    public void setIsDotNeeded(boolean isNeeded) {
        isDot = isNeeded;
    }

    public static class CaptchaGenerator {
        public static final int ALPHABETS = 1, NUMBERS = 2, BOTH = 3;

        private static Captcha regenerate(int width, int height, int length, int type, boolean isDot) {
            Paint border = new Paint();
            border.setStyle(Paint.Style.STROKE);
            border.setColor(Color.parseColor("#CCCCCC"));
            Paint paint = new Paint();
            paint.setColor(Color.BLACK);
            paint.setStyle(Paint.Style.FILL_AND_STROKE);
            if (isDot)
                paint.setTypeface(Typeface.DEFAULT_BOLD);
            else
                paint.setTypeface(Typeface.MONOSPACE);
            Bitmap bitMap = Bitmap.createBitmap(width,
                    height,
                    Bitmap.Config.ARGB_8888);
            Canvas canvas = new Canvas(bitMap);
            canvas.drawColor(Color.parseColor("#F7F7FF"));
            int textX = generateRandomInt(width - ((width / 5) * 4), width / 2);
            int textY = generateRandomInt(height - ((height / 3)), height - (height / 4));
            String generatedText = drawRandomText(canvas, paint, textX, textY, length, type, isDot);
            if (isDot) {
                canvas.drawLine(textX, textY - generateRandomInt(7, 10), textX + (length * 33), textY - generateRandomInt(5, 10), paint);
                canvas.drawLine(textX, textY - generateRandomInt(7, 10), textX + (length * 33), textY - generateRandomInt(5, 10), paint);
            } else {
                canvas.drawLine(textX, textY - generateRandomInt(7, 10), textX + (length * 23), textY - generateRandomInt(5, 10), paint);
                canvas.drawLine(textX, textY - generateRandomInt(7, 10), textX + (length * 23), textY - generateRandomInt(5, 10), paint);
            }
            canvas.drawRect(0, 0, width - 1, height - 1, border);
            if (isDot)
                makeDots(bitMap, width, height, textX, textY);
            return (new Captcha(generatedText, bitMap));
        }

        private static void makeDots(Bitmap bitMap, int width, int height, int textX, int textY) {
            int white = -526337;
            int black = -16777216;
            int grey=-3355444;
            Random random = new Random();
            for (int x = 0; x < width; x++) {
                for (int y = 0; y < height; y++) {
                    int pixel = bitMap.getPixel(x, y);
                    if (pixel == white) {
                        pixel = (random.nextBoolean()) ? black : white;
                    }
                    bitMap.setPixel(x, y, pixel);
                }
            }
        }

        private static String drawRandomText(Canvas canvas, Paint paint, int textX, int textY, int length, int type, boolean isDot) {
            String generatedCaptcha = "";
            int[] scewRange = {-1, 1};
            int[] textSizeRange = {40, 42, 44, 45};
            Random random = new Random();
            paint.setTextSkewX(scewRange[random.nextInt(scewRange.length)]);
            for (int index = 0; index < length; index++) {
                String temp = generateRandomText(type);
                generatedCaptcha = generatedCaptcha + temp;
                paint.setTextSize(textSizeRange[random.nextInt(textSizeRange.length)]);
                if (isDot)
                    canvas.drawText(temp, textX + (index * 25), textY, paint);
                else
                    canvas.drawText(temp, textX + (index * 20), textY, paint);
            }
            return generatedCaptcha;
        }

        private static String generateRandomText(int type) {
            String[] numbers = {"0", "1", "2", "3", "4", "5", "6", "7", "8", "9"};
            String[] alphabets = {"a", "b", "c", "d", "e", "f", "g", "h", "i", "j", "k", "l", "m", "n", "o", "p", "q", "r", "s", "t", "u", "v", "w", "x", "y", "z"
                    , "A", "B", "C", "D", "E", "F", "G", "H", "I", "J", "K", "L", "M", "N", "O", "P", "Q", "R", "S", "T", "U", "V", "W", "X", "Y", "Z"};
            Random random = new Random();
            Random mixedRandom = new Random();
            String temp;
            if (type == ALPHABETS)
                temp = alphabets[random.nextInt(alphabets.length)];
            else if (type == NUMBERS)
                temp = numbers[random.nextInt(numbers.length)];
            else
                temp = (mixedRandom.nextBoolean()) ? (alphabets[random.nextInt(alphabets.length)]) : (numbers[random.nextInt(numbers.length)]);
            return temp;
        }

        private static int generateRandomInt(int length) {
            Random random = new Random();
            int ran = random.nextInt(length);
            return (ran == 0) ? random.nextInt(length) : ran;
        }

        private static int generateRandomInt(int min, int max) {
            Random rand = new Random();
            return rand.nextInt((max - min) + 1) + min;
        }

        private static class Captcha {
            private String captchaCode;
            private Bitmap bitmap;

            Captcha(String captchaCode, Bitmap bitmap) {
                this.captchaCode = captchaCode;
                this.bitmap = bitmap;
            }

            String getCaptchaCode() {
                return captchaCode;
            }

            public void setCaptchaCode(String captchaCode) {
                this.captchaCode = captchaCode;
            }

            Bitmap getBitmap() {
                return bitmap;
            }

            public void setBitmap(Bitmap bitmap) {
                this.bitmap = bitmap;
            }
        }

    }

    @Override
    public void onWindowFocusChanged(boolean hasWindowFocus) {
        super.onWindowFocusChanged(hasWindowFocus);
        if(!isRedraw){
            reDraw();
            isRedraw=true;
        }

    }
}
