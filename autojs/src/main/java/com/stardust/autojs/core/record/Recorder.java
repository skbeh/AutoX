package com.stardust.autojs.core.record;

import java.util.Arrays;

/**
 * Created by Stardust on 2017/3/16.
 */

public interface Recorder {

    int STATE_NOT_START = 0;
    int STATE_RECORDING = 1;
    int STATE_PAUSED = 2;
    int STATE_STOPPED = 3;

    void start();

    void stop();

    void pause();

    void resume();

    String getCode();

    String getPath();

    int getState();

    void setOnStateChangedListener(OnStateChangedListener onStateChangedListener);

    interface OnStateChangedListener {

        void onStart();

        void onStop();

        void onPause();

        void onResume();

    }

    class StateChangeEvent {

        private final int mOldState;
        private final int mCurrentState;

        public StateChangeEvent(int oldState, int currentState) {
            mOldState = oldState;
            mCurrentState = currentState;
        }

        public int getOldState() {
            return mOldState;
        }

        public int getCurrentState() {
            return mCurrentState;
        }
    }

    abstract class AbstractRecorder implements Recorder {

        private static final OnStateChangedListener NO_OPERATION_LISTENER = new OnStateChangedListener() {
            @Override
            public void onStart() {

            }

            @Override
            public void onStop() {

            }

            @Override
            public void onPause() {

            }

            @Override
            public void onResume() {

            }
        };
        private final boolean mSync;
        private OnStateChangedListener mOnStateChangedListener = NO_OPERATION_LISTENER;
        private int mState = STATE_NOT_START;

        public AbstractRecorder(boolean syncOfState) {
            mSync = syncOfState;
        }

        public AbstractRecorder() {
            this(false);
        }

        public void start() {
            ensureIsStateOf(STATE_NOT_START);
            setState(STATE_RECORDING);
            startImpl();
            mOnStateChangedListener.onStart();
        }


        private void ensureIsStateOf(int... expectedStates) {
            for (int expectedState : expectedStates) {
                if (mState == expectedState)
                    return;
            }
            throw new IllegalStateException("expected=" + Arrays.toString(expectedStates) + " state=" + mState);
        }


        protected abstract void startImpl();

        public void stop() {
            ensureIsStateOf(STATE_RECORDING, STATE_PAUSED);
            setState(STATE_STOPPED);
            stopImpl();
            mOnStateChangedListener.onStop();
        }

        protected abstract void stopImpl();

        public void pause() {
            ensureIsStateOf(STATE_RECORDING);
            setState(STATE_PAUSED);
            pauseImpl();
            mOnStateChangedListener.onPause();
        }

        public synchronized int getState() {
            if (mSync) {
                synchronized (this) {
                    return mState;
                }
            } else {
                return mState;
            }
        }

        protected synchronized void setState(int state) {
            if (mSync) {
                synchronized (this) {
                    mState = state;
                }
            } else {
                mState = state;
            }
        }

        protected void pauseImpl() {

        }

        public void resume() {
            ensureIsStateOf(STATE_PAUSED);
            setState(STATE_RECORDING);
            resumeImpl();
            mOnStateChangedListener.onResume();
        }

        protected void resumeImpl() {

        }

        public void setOnStateChangedListener(OnStateChangedListener onStateChangedListener) {
            mOnStateChangedListener = onStateChangedListener == null ? NO_OPERATION_LISTENER : onStateChangedListener;
        }

        @Override
        public String getPath() {
            return null;
        }
    }
}
