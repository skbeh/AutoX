package com.stardust.autojs.core.looper;

import com.stardust.autojs.runtime.ScriptRuntime;
import com.stardust.lang.ThreadCompat;

import java.util.Map;

/**
 * Created by Stardust on 2017/12/28.
 */

public class MainThreadProxy {

    private final Thread mThread;
    private final ScriptRuntime mRuntime;

    public MainThreadProxy(Thread thread, ScriptRuntime runtime) {
        mThread = thread;
        mRuntime = runtime;
    }

    public static Thread currentThread() {
        return Thread.currentThread();
    }

    public static void yield() {
        Thread.yield();
    }

    public static void sleep(long millis) throws InterruptedException {
        Thread.sleep(millis);
    }

    public static void sleep(long millis, int nanos) throws InterruptedException {
        Thread.sleep(millis, nanos);
    }

    public static boolean interrupted() {
        return ThreadCompat.interrupted();
    }

    public static int activeCount() {
        return Thread.activeCount();
    }

    public static int enumerate(Thread[] tarray) {
        return Thread.enumerate(tarray);
    }

    public static void dumpStack() {
        Thread.dumpStack();
    }

    public static boolean holdsLock(Object obj) {
        return Thread.holdsLock(obj);
    }

    public static Map<Thread, StackTraceElement[]> getAllStackTraces() {
        return Thread.getAllStackTraces();
    }

    public static Thread.UncaughtExceptionHandler getDefaultUncaughtExceptionHandler() {
        return Thread.getDefaultUncaughtExceptionHandler();
    }

    public static void setDefaultUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) {
        Thread.setDefaultUncaughtExceptionHandler(eh);
    }

    public int setTimeout(Object callback, long delay, Object... args) {
        return getMainTimer().setTimeout(callback, delay, args);
    }

    private Timer getMainTimer() {
        return mRuntime.timers.getMainTimer();
    }

    public boolean clearTimeout(int id) {
        return getMainTimer().clearTimeout(id);
    }

    public int setInterval(Object listener, long interval, Object... args) {
        return getMainTimer().setInterval(listener, interval, args);
    }

    public boolean clearInterval(int id) {
        return getMainTimer().clearInterval(id);
    }

    public int setImmediate(Object listener, Object... args) {
        return getMainTimer().setImmediate(listener, args);
    }

    public boolean clearImmediate(int id) {
        return getMainTimer().clearImmediate(id);
    }

    public void start() {
        mThread.start();
    }

    public void run() {
        mThread.run();
    }

    @Deprecated
    public void stop() {
        mThread.stop();
    }

    @Deprecated
    public void stop(Throwable obj) {
        mThread.stop(obj);
    }

    public void interrupt() {
        mThread.interrupt();
    }

    public boolean isInterrupted() {
        return mThread.isInterrupted();
    }

    @Deprecated
    public void destroy() {
        mThread.destroy();
    }

    public boolean isAlive() {
        return mThread.isAlive();
    }

    @Deprecated
    public void suspend() {
        mThread.suspend();
    }

    @Deprecated
    public void resume() {
        mThread.resume();
    }

    public int getPriority() {
        return mThread.getPriority();
    }

    public void setPriority(int newPriority) {
        mThread.setPriority(newPriority);
    }

    public String getName() {
        return mThread.getName();
    }

    public void setName(String name) {
        mThread.setName(name);
    }

    public ThreadGroup getThreadGroup() {
        return mThread.getThreadGroup();
    }

    @Deprecated
    public int countStackFrames() {
        return mThread.countStackFrames();
    }

    public void join(long millis) throws InterruptedException {
        mThread.join(millis);
    }

    public void join(long millis, int nanos) throws InterruptedException {
        mThread.join(millis, nanos);
    }

    public void join() throws InterruptedException {
        mThread.join();
    }

    public boolean isDaemon() {
        return mThread.isDaemon();
    }

    public void setDaemon(boolean on) {
        mThread.setDaemon(on);
    }

    public void checkAccess() {
        mThread.checkAccess();
    }

    public ClassLoader getContextClassLoader() {
        return mThread.getContextClassLoader();
    }

    public void setContextClassLoader(ClassLoader cl) {
        mThread.setContextClassLoader(cl);
    }

    public StackTraceElement[] getStackTrace() {
        return mThread.getStackTrace();
    }

    public long getId() {
        return mThread.getId();
    }

    public Thread.State getState() {
        return mThread.getState();
    }

    @Override
    public String toString() {
        return mThread.toString();
    }

    public Thread.UncaughtExceptionHandler getUncaughtExceptionHandler() {
        return mThread.getUncaughtExceptionHandler();
    }

    public void setUncaughtExceptionHandler(Thread.UncaughtExceptionHandler eh) {
        mThread.setUncaughtExceptionHandler(eh);
    }
}
