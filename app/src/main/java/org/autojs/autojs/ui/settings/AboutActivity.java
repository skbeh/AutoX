package org.autojs.autojs.ui.settings;

import android.annotation.SuppressLint;
import android.widget.TextView;
import android.widget.Toast;

import com.stardust.util.IntentUtil;

import org.androidannotations.annotations.AfterViews;
import org.androidannotations.annotations.Click;
import org.androidannotations.annotations.EActivity;
import org.androidannotations.annotations.ViewById;
import org.autojs.autojs.BuildConfig;
import org.autojs.autojs.R;
import org.autojs.autojs.tool.IntentTool;
import org.autojs.autojs.ui.BaseActivity;

/**
 * Created by Stardust on 2017/2/2.
 */
@EActivity(R.layout.activity_about)
public class AboutActivity extends BaseActivity {

    private static final String TAG = "AboutActivity";
    @ViewById(R.id.version)
    TextView mVersion;

    private final int mLolClickCount = 0;


    @AfterViews
    void setUpViews() {
        setVersionName();
        setToolbarAsBack(getString(R.string.text_about));
    }

    @SuppressLint("SetTextI18n")
    private void setVersionName() {
        mVersion.setText("Version " + BuildConfig.VERSION_NAME);
    }

    @Click(R.id.github)
    void openGitHub() {
        IntentTool.browse(this, getString(R.string.my_github));
    }

    @Click(R.id.qq)
    void openQQToChatWithMe() {
        String qq = getString(R.string.qq);
        Toast.makeText(this, R.string.text_mobile_qq_not_installed, Toast.LENGTH_SHORT).show();
        if (!IntentUtil.joinQQGroup(this, qq)) {
           // Toast.makeText(this, R.string.text_mobile_qq_not_installed, Toast.LENGTH_SHORT).show();
        }
    }

    @Click(R.id.email)
    void openEmailToSendMe() {
        String email = getString(R.string.email);
        IntentUtil.sendMailTo(this, email);
    }
    @Click(R.id.share)
    void share() {
        IntentUtil.shareText(this, getString(R.string.share_app));
    }

    @Click(R.id.developer)
    void hhh() {
        Toast.makeText(this, R.string.text_it_is_the_developer_of_app, Toast.LENGTH_LONG).show();
    }


}
