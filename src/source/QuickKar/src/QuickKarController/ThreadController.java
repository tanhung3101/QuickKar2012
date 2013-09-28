/**
 * Course: Software Development: Process & Tools
 * Lecturer: Quang Tran
 * Assignment: 2-2012B
 * Assignment Title: QuickKar2012
 *
 *      RMIT International University Vietnam
 * Bachelor of Information Technology - Application
 *
 */
package QuickKarController;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
/**
 * Create and maintain threads
 * 
 * @author vuongdothanhhuy
 */
public class ThreadController {

    private static ExecutorService threadPool = Executors.newCachedThreadPool();

    public static ExecutorService getThreadPool() {
        return threadPool;
    }
}
