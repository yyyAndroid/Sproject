package com.abe.dwwd.sporjectone.view;

import android.content.Context;
import android.content.res.TypedArray;
import android.hardware.Camera;
import android.media.MediaRecorder;
import android.os.Environment;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.SurfaceHolder;
import android.view.SurfaceView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;

import com.abe.dwwd.sporjectone.R;

import java.io.File;
import java.io.IOException;
import java.util.Timer;
import java.util.TimerTask;

/**
 * Created by Administrator on 2017/2/7.
 */

public class MoveRecorderView extends LinearLayout implements MediaRecorder.OnErrorListener {
    private SurfaceView mSurfaceView;
    private SurfaceHolder mSurfaceHolder;
    private ProgressBar mProgressBar;

    private MediaRecorder mMediaRecorder;
    private Camera mCamera;
    private Timer mTimer;//计时器

    private int mWidth;//视频分辨率宽度
    private int mHeight;//视频分辨率高度

    private boolean isOpenCamera;//是否一开始就开摄像头

    private int mRecordMaxTime;//一次拍摄最长时间
    private int mTimeCount;//时间计算
    private File mVecordFile = null;//文件

    private OnRecordFinishListener  mOnRecordFinishListener;
    public MoveRecorderView(Context context) {
        this(context,null);
    }

    public MoveRecorderView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public MoveRecorderView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);

        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.MoveRecorderView,defStyleAttr,0);
        mWidth = a.getInteger(R.styleable.MoveRecorderView_swidth,320);
        mHeight = a.getInteger(R.styleable.MoveRecorderView_sheight,240);

        isOpenCamera = a.getBoolean(R.styleable.MoveRecorderView_is_open_camera,false);
        mRecordMaxTime = a.getInteger(R.styleable.MoveRecorderView_record_max_time,10);

        a.recycle();

        LayoutInflater.from(context).inflate(R.layout.movie_recorder,this);
        mSurfaceView = (SurfaceView) findViewById(R.id.movie_suface_view);
        mProgressBar = (ProgressBar) findViewById(R.id.movie_progress_bar);

        mSurfaceHolder = mSurfaceView.getHolder();
        mSurfaceHolder.addCallback(new CustomCallBack());
    }

    @Override
    public void onError(MediaRecorder mr, int what, int extra) {
        try {
            if (mr != null)
                mr.reset();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }


    /**
     *
     */
    private class CustomCallBack implements SurfaceHolder.Callback {

        @Override
        public void surfaceCreated(SurfaceHolder holder) {
            if (!isOpenCamera)
                return;
            try {
                initCamera();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

        @Override
        public void surfaceChanged(SurfaceHolder holder, int format, int width,
                                   int height) {

        }

        @Override
        public void surfaceDestroyed(SurfaceHolder holder) {
            if (!isOpenCamera)
                return;
            freeCameraResource();
        }

    }
    private void initCamera(){
        if (mCamera != null) {
            freeCameraResource();
        }
        try {
            mCamera = Camera.open();
        } catch (Exception e) {
            e.printStackTrace();
            freeCameraResource();
        }
        if (mCamera == null)
            return;

        setCameraParams();
        mCamera.setDisplayOrientation(90);
        try {
            mCamera.setPreviewDisplay(mSurfaceHolder);
        } catch (IOException e) {
            e.printStackTrace();
        }
        mCamera.startPreview();
        mCamera.unlock();
    }

    /**
     * 设置摄像头为竖屏
     */
    private void setCameraParams() {
        if (mCamera != null){
            Camera.Parameters params = mCamera.getParameters();
            params.set("orientation","portrait");
            mCamera.setParameters(params);
        }
    }

    /**
     * 释放摄像头资源
     */
    private void freeCameraResource(){
        if (mCamera != null){
            mCamera.setPreviewCallback(null);
            mCamera.stopPreview();
            mCamera.lock();
            mCamera = null;
        }
    }

    private void createRecordDir(){
        //录制的视频保存文件
        File sampleDir = new File(Environment.getExternalStorageDirectory()
                +File.separator + "video/");//录制视频的保存地址
        if (!sampleDir.exists()){
            sampleDir.mkdirs();
        }
        File vecordDir = sampleDir;
        //创建文件
        try {
            mVecordFile = File.createTempFile("recording",".mp4",vecordDir);//mp4格式的录制的视频
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    /**
     * 初始化
     */
    private void initRecord() throws IOException {
        mMediaRecorder = new MediaRecorder();
        mMediaRecorder.reset();
        if (mCamera != null){
            mMediaRecorder.setCamera(mCamera);
        }
        mMediaRecorder.setOnErrorListener(this);
        mMediaRecorder.setPreviewDisplay(mSurfaceHolder.getSurface());
        mMediaRecorder.setVideoSource(MediaRecorder.VideoSource.CAMERA);// 视频源
        mMediaRecorder.setAudioSource(MediaRecorder.AudioSource.MIC);// 音频源
        mMediaRecorder.setOutputFormat(MediaRecorder.OutputFormat.MPEG_4);// 视频输出格式
        mMediaRecorder.setAudioEncoder(MediaRecorder.AudioEncoder.AMR_NB);// 音频格式
        mMediaRecorder.setVideoSize(mWidth, mHeight);// 设置分辨率：
        // mMediaRecorder.setVideoFrameRate(16);// 这个我把它去掉了，感觉没什么用
        mMediaRecorder.setVideoEncodingBitRate(1 * 1024 * 1024 * 100);// 设置帧频率，然后就清晰了
        mMediaRecorder.setOrientationHint(90);// 输出旋转90度，保持竖屏录制
        mMediaRecorder.setVideoEncoder(MediaRecorder.VideoEncoder.MPEG_4_SP);// 视频录制格式
        // mediaRecorder.setMaxDuration(Constant.MAXVEDIOTIME * 1000);
        mMediaRecorder.setOutputFile(mVecordFile.getAbsolutePath());
        mMediaRecorder.prepare();
        try {
            mMediaRecorder.start();
        } catch (IllegalStateException e) {
            e.printStackTrace();
        } catch (RuntimeException e) {
            e.printStackTrace();
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 开始录制视频
     * @param onRecordFinishListener
     */
    public void record(final OnRecordFinishListener onRecordFinishListener){
        this.mOnRecordFinishListener = onRecordFinishListener;
        createRecordDir();
        if (!isOpenCamera){//如果摄像头未打开
            initCamera();
        }
        try {
            initRecord();
            mTimeCount = 0;//时间计数器重新赋值
            mTimer = new Timer();

            mTimer.schedule(new TimerTask() {
                @Override
                public void run() {
                    mTimeCount++;
                    mProgressBar.setProgress(mTimeCount);// 设置进度条
                    if (mTimeCount == mRecordMaxTime) {// 达到指定时间，停止拍摄
                        stop();
                        if (mOnRecordFinishListener != null)
                            mOnRecordFinishListener.onRecordFinish();
                    }
                }
            },0,1000);

        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    /**
     * 停止拍摄
     */
    public void stop() {
        stopRecord();
        releaseRecord();
        freeCameraResource();
    }

    /**
     * 停止录制
     */
    public void stopRecord() {
        mProgressBar.setProgress(0);
        if (mTimer != null)
            mTimer.cancel();
        if (mMediaRecorder != null) {
            // 设置后不会崩
            mMediaRecorder.setOnErrorListener(null);
            mMediaRecorder.setPreviewDisplay(null);
            try {
                mMediaRecorder.stop();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (RuntimeException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 释放资源
     */
    private void releaseRecord() {
        if (mMediaRecorder != null) {
            mMediaRecorder.setOnErrorListener(null);
            try {
                mMediaRecorder.release();
            } catch (IllegalStateException e) {
                e.printStackTrace();
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        mMediaRecorder = null;
    }

    public int getTimeCount() {
        return mTimeCount;
    }

    //返回录制的视频文件
    public File getmVecordFile() {
        return mVecordFile;
    }


    /**
     * 录制完成回调接口
     */
    public interface OnRecordFinishListener {
        public void onRecordFinish();
    }
}
