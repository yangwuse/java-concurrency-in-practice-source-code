package ch2.guardingstatewithlocks;

import java.util.concurrent.TimeUnit;

/**
 * Coordinate a variable needs synchronization everywhere it presences
 * @author yangWu
 * @date 2021/4/29 11:50 上午
 * @version 1.0
 */
public class CommonMistake {
  static boolean flag;
  static synchronized void write() { flag = true; }
  static synchronized boolean read() { return flag; } // must synchronize read

  public static void main(String[] args) throws InterruptedException {
    new Thread(() -> {
      while (!read());
    }).start();

    TimeUnit.SECONDS.sleep(1);
    write(); // stop after 1 secs
  }
}
