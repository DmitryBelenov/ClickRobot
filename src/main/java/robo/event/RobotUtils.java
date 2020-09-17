package robo.event;

import java.awt.event.KeyEvent;
import java.util.HashMap;
import java.util.Map;

class RobotUtils {
    static Map<String, Integer> keyboardMap = new HashMap<String, Integer>();
    static {
        /* спецсимволы */
        keyboardMap.put(" ", KeyEvent.VK_SPACE);

        /* латиница */
        keyboardMap.put("а", KeyEvent.VK_F);
        keyboardMap.put("б", KeyEvent.VK_D);
        keyboardMap.put("в", KeyEvent.VK_D);
        keyboardMap.put("г", KeyEvent.VK_U);
        keyboardMap.put("д", KeyEvent.VK_L);
        keyboardMap.put("е", KeyEvent.VK_T);
        keyboardMap.put("ё", KeyEvent.VK_T);
        keyboardMap.put("ж", KeyEvent.VK_L);
        keyboardMap.put("з", KeyEvent.VK_P);
        keyboardMap.put("и", KeyEvent.VK_B);
        keyboardMap.put("й", KeyEvent.VK_B);
        keyboardMap.put("к", KeyEvent.VK_R);
        keyboardMap.put("л", KeyEvent.VK_K);
        keyboardMap.put("м", KeyEvent.VK_V);
        keyboardMap.put("н", KeyEvent.VK_Y);
        keyboardMap.put("о", KeyEvent.VK_J);
        keyboardMap.put("п", KeyEvent.VK_G);
        keyboardMap.put("р", KeyEvent.VK_H);
        keyboardMap.put("с", KeyEvent.VK_C);
        keyboardMap.put("т", KeyEvent.VK_N);
        keyboardMap.put("у", KeyEvent.VK_E);
        keyboardMap.put("ф", KeyEvent.VK_A);
        keyboardMap.put("х", KeyEvent.VK_X);
        keyboardMap.put("ц", KeyEvent.VK_W);
        keyboardMap.put("ч", KeyEvent.VK_X);
        keyboardMap.put("ш", KeyEvent.VK_I);
        keyboardMap.put("щ", KeyEvent.VK_O);
        keyboardMap.put("ъ", KeyEvent.VK_P);
        keyboardMap.put("ы", KeyEvent.VK_S);
        keyboardMap.put("ь", KeyEvent.VK_M);
        keyboardMap.put("э", KeyEvent.VK_L);
        keyboardMap.put("ю", KeyEvent.VK_M);
        keyboardMap.put("я", KeyEvent.VK_Z);

        /* цифры */
        keyboardMap.put("0", KeyEvent.VK_0);
        keyboardMap.put("1", KeyEvent.VK_1);
        keyboardMap.put("2", KeyEvent.VK_2);
        keyboardMap.put("3", KeyEvent.VK_3);
        keyboardMap.put("4", KeyEvent.VK_4);
        keyboardMap.put("5", KeyEvent.VK_5);
        keyboardMap.put("6", KeyEvent.VK_6);
        keyboardMap.put("7", KeyEvent.VK_7);
        keyboardMap.put("8", KeyEvent.VK_8);
        keyboardMap.put("9", KeyEvent.VK_9);

        /* английские */
        keyboardMap.put("a", KeyEvent.VK_A);
        keyboardMap.put("b", KeyEvent.VK_B);
        keyboardMap.put("c", KeyEvent.VK_C);
        keyboardMap.put("d", KeyEvent.VK_D);
        keyboardMap.put("e", KeyEvent.VK_E);
        keyboardMap.put("f", KeyEvent.VK_F);
        keyboardMap.put("g", KeyEvent.VK_G);
        keyboardMap.put("h", KeyEvent.VK_H);
        keyboardMap.put("i", KeyEvent.VK_I);
        keyboardMap.put("j", KeyEvent.VK_J);
        keyboardMap.put("k", KeyEvent.VK_K);
        keyboardMap.put("l", KeyEvent.VK_L);
        keyboardMap.put("m", KeyEvent.VK_M);
        keyboardMap.put("n", KeyEvent.VK_N);
        keyboardMap.put("o", KeyEvent.VK_O);
        keyboardMap.put("p", KeyEvent.VK_P);
        keyboardMap.put("q", KeyEvent.VK_Q);
        keyboardMap.put("r", KeyEvent.VK_R);
        keyboardMap.put("s", KeyEvent.VK_S);
        keyboardMap.put("t", KeyEvent.VK_T);
        keyboardMap.put("u", KeyEvent.VK_U);
        keyboardMap.put("v", KeyEvent.VK_V);
        keyboardMap.put("w", KeyEvent.VK_W);
        keyboardMap.put("x", KeyEvent.VK_X);
        keyboardMap.put("y", KeyEvent.VK_Y);
        keyboardMap.put("z", KeyEvent.VK_Z);
    }
}
