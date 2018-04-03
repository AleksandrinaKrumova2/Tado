package com.tado.android.client;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.os.AsyncTask;
import android.support.v7.widget.helper.ItemTouchHelper.Callback;
import com.tado.C0676R;
import com.tado.android.PromoActivity;
import com.tado.android.app.TadoApplication;
import com.tado.android.controllers.NavigationController;
import com.tado.android.login.LoginActivity;
import com.tado.android.requests.Request;
import com.tado.android.responses.Response;
import com.tado.android.utils.Snitcher;
import com.tado.android.utils.Util;
import java.io.InputStream;
import java.lang.ref.WeakReference;
import java.util.Scanner;

public class APICall extends AsyncTask<Void, Void, Response> {
    private static final String TAG = APICall.class.getCanonicalName();
    private WeakReference<Activity> mContext;
    protected APICallListener mListener;
    private WeakReference<ProgressDialog> mProgress;
    protected Request mRequest;
    private boolean mShowLoaderDialog = false;

    class C07361 extends Response {
        C07361() {
        }

        public void parse(String stream) {
        }
    }

    public APICall(Request request, Activity context) {
        setRequest(request);
        this.mContext = new WeakReference(context);
    }

    public void setAPICallListener(APICallListener listener) {
        this.mListener = listener;
    }

    public Request getRequest() {
        return this.mRequest;
    }

    protected void onPreExecute() {
        if (this.mShowLoaderDialog && this.mContext.get() != null && !((Activity) this.mContext.get()).isFinishing()) {
            ProgressDialog progressDialog = new ProgressDialog((Context) this.mContext.get());
            progressDialog.setCancelable(false);
            progressDialog.setMessage(((Activity) this.mContext.get()).getResources().getString(C0676R.string.loading_title));
            progressDialog.setCanceledOnTouchOutside(false);
            progressDialog.show();
            this.mProgress = new WeakReference(progressDialog);
        }
    }

    /* JADX WARNING: inconsistent code. */
    /* Code decompiled incorrectly, please refer to instructions dump. */
    protected com.tado.android.responses.Response doInBackground(java.lang.Void... r23) {
        /*
        r22 = this;
        r8 = 0;
        r17 = com.tado.android.utils.Util.isNetworkAvailable();
        if (r17 != 0) goto L_0x0009;
    L_0x0007:
        r11 = 0;
    L_0x0008:
        return r11;
    L_0x0009:
        r13 = com.tado.android.utils.UserConfig.getServerAddress();
        r17 = r22.getRequest();
        r9 = r17.toBytes();
        r16 = new java.net.URL;	 Catch:{ MalformedURLException -> 0x0141 }
        r17 = new java.lang.StringBuilder;	 Catch:{ MalformedURLException -> 0x0141 }
        r17.<init>();	 Catch:{ MalformedURLException -> 0x0141 }
        r0 = r17;
        r17 = r0.append(r13);	 Catch:{ MalformedURLException -> 0x0141 }
        r18 = r22.getRequest();	 Catch:{ MalformedURLException -> 0x0141 }
        r18 = r18.getUrl();	 Catch:{ MalformedURLException -> 0x0141 }
        r17 = r17.append(r18);	 Catch:{ MalformedURLException -> 0x0141 }
        r17 = r17.toString();	 Catch:{ MalformedURLException -> 0x0141 }
        r16.<init>(r17);	 Catch:{ MalformedURLException -> 0x0141 }
        r2 = "";
        if (r9 == 0) goto L_0x0045;
    L_0x003a:
        r3 = new java.lang.String;	 Catch:{ UnsupportedEncodingException -> 0x0156 }
        r17 = "UTF-8";
        r0 = r17;
        r3.<init>(r9, r0);	 Catch:{ UnsupportedEncodingException -> 0x0156 }
        r2 = r3;
    L_0x0045:
        r4 = 0;
        r17 = "https";
        r0 = r17;
        r17 = r13.contains(r0);	 Catch:{ IOException -> 0x0179 }
        if (r17 == 0) goto L_0x016a;
    L_0x0051:
        r17 = r16.openConnection();	 Catch:{ IOException -> 0x0179 }
        r0 = r17;
        r0 = (javax.net.ssl.HttpsURLConnection) r0;	 Catch:{ IOException -> 0x0179 }
        r4 = r0;
    L_0x005a:
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x0179 }
        r18 = 3;
        r19 = TAG;	 Catch:{ IOException -> 0x0179 }
        r20 = "open connection";
        r21 = 0;
        r0 = r21;
        r0 = new java.lang.Object[r0];	 Catch:{ IOException -> 0x0179 }
        r21 = r0;
        r17.log(r18, r19, r20, r21);	 Catch:{ IOException -> 0x0179 }
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r17 = r17.getHttpMethod();	 Catch:{ IOException -> 0x0179 }
        r0 = r17;
        r4.setRequestMethod(r0);	 Catch:{ IOException -> 0x0179 }
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r17 = r17.getHttpMethod();	 Catch:{ IOException -> 0x0179 }
        r18 = "POST";
        r17 = r17.equals(r18);	 Catch:{ IOException -> 0x0179 }
        if (r17 != 0) goto L_0x009f;
    L_0x008e:
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r17 = r17.getHttpMethod();	 Catch:{ IOException -> 0x0179 }
        r18 = "PUT";
        r17 = r17.equals(r18);	 Catch:{ IOException -> 0x0179 }
        if (r17 == 0) goto L_0x0175;
    L_0x009f:
        r17 = 1;
    L_0x00a1:
        r0 = r17;
        r4.setDoOutput(r0);	 Catch:{ IOException -> 0x0179 }
        r17 = "Content-Type";
        r18 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r18 = r18.getContentType();	 Catch:{ IOException -> 0x0179 }
        r0 = r17;
        r1 = r18;
        r4.setRequestProperty(r0, r1);	 Catch:{ IOException -> 0x0179 }
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r17 = r17.isUseEtag();	 Catch:{ IOException -> 0x0179 }
        if (r17 == 0) goto L_0x00de;
    L_0x00c2:
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r17 = r17.getEtag();	 Catch:{ IOException -> 0x0179 }
        if (r17 == 0) goto L_0x00de;
    L_0x00cc:
        r17 = "If-None-Match";
        r18 = r22.getRequest();	 Catch:{ IOException -> 0x0179 }
        r18 = r18.getEtag();	 Catch:{ IOException -> 0x0179 }
        r0 = r17;
        r1 = r18;
        r4.setRequestProperty(r0, r1);	 Catch:{ IOException -> 0x0179 }
    L_0x00de:
        if (r9 == 0) goto L_0x00e8;
    L_0x00e0:
        r0 = r9.length;	 Catch:{ IOException -> 0x0179 }
        r17 = r0;
        r0 = r17;
        r4.setFixedLengthStreamingMode(r0);	 Catch:{ IOException -> 0x0179 }
    L_0x00e8:
        r17 = android.os.Build.VERSION.SDK;	 Catch:{ IOException -> 0x0179 }
        if (r17 == 0) goto L_0x0103;
    L_0x00ec:
        r17 = android.os.Build.VERSION.SDK_INT;	 Catch:{ IOException -> 0x0179 }
        r18 = 13;
        r0 = r17;
        r1 = r18;
        if (r0 <= r1) goto L_0x0103;
    L_0x00f6:
        r17 = "Connection";
        r18 = "close";
        r0 = r17;
        r1 = r18;
        r4.setRequestProperty(r0, r1);	 Catch:{ IOException -> 0x0179 }
    L_0x0103:
        r10 = 0;
        if (r9 == 0) goto L_0x0117;
    L_0x0106:
        r17 = r22.getRequest();	 Catch:{ all -> 0x01a1 }
        r17 = r17.getHttpMethod();	 Catch:{ all -> 0x01a1 }
        r18 = "POST";
        r17 = r17.equals(r18);	 Catch:{ all -> 0x01a1 }
        if (r17 != 0) goto L_0x0128;
    L_0x0117:
        r17 = r22.getRequest();	 Catch:{ all -> 0x01a1 }
        r17 = r17.getHttpMethod();	 Catch:{ all -> 0x01a1 }
        r18 = "PUT";
        r17 = r17.equals(r18);	 Catch:{ all -> 0x01a1 }
        if (r17 == 0) goto L_0x0236;
    L_0x0128:
        r10 = r4.getOutputStream();	 Catch:{ IOException -> 0x01a8, Throwable -> 0x01dd }
        r10.write(r9);	 Catch:{ IOException -> 0x01a8, Throwable -> 0x01dd }
        r10.close();	 Catch:{ IOException -> 0x01a8, Throwable -> 0x01dd }
        if (r10 == 0) goto L_0x0137;
    L_0x0134:
        r10.close();	 Catch:{ IOException -> 0x018e }
    L_0x0137:
        if (r8 == 0) goto L_0x0236;
    L_0x0139:
        r11 = 0;
        if (r4 == 0) goto L_0x0008;
    L_0x013c:
        r4.disconnect();
        goto L_0x0008;
    L_0x0141:
        r6 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();
        r17 = r17.toCrashlytics();
        r18 = TAG;
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r6);
        r11 = 0;
        goto L_0x0008;
    L_0x0156:
        r7 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();
        r17 = r17.toCrashlytics();
        r18 = TAG;
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r7);
        goto L_0x0045;
    L_0x016a:
        r17 = r16.openConnection();	 Catch:{ IOException -> 0x0179 }
        r0 = r17;
        r0 = (java.net.HttpURLConnection) r0;	 Catch:{ IOException -> 0x0179 }
        r4 = r0;
        goto L_0x005a;
    L_0x0175:
        r17 = 0;
        goto L_0x00a1;
    L_0x0179:
        r6 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();
        r17 = r17.toCrashlytics();
        r18 = TAG;
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r6);
        r11 = 0;
        goto L_0x0008;
    L_0x018e:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x0137;
    L_0x01a1:
        r17 = move-exception;
        if (r4 == 0) goto L_0x01a7;
    L_0x01a4:
        r4.disconnect();
    L_0x01a7:
        throw r17;
    L_0x01a8:
        r6 = move-exception;
        r8 = 1;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x0212 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x0212 }
        r18 = TAG;	 Catch:{ all -> 0x0212 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r6);	 Catch:{ all -> 0x0212 }
        if (r10 == 0) goto L_0x01c0;
    L_0x01bd:
        r10.close();	 Catch:{ IOException -> 0x01ca }
    L_0x01c0:
        if (r8 == 0) goto L_0x0236;
    L_0x01c2:
        r11 = 0;
        if (r4 == 0) goto L_0x0008;
    L_0x01c5:
        r4.disconnect();
        goto L_0x0008;
    L_0x01ca:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x01c0;
    L_0x01dd:
        r15 = move-exception;
        r8 = 1;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x0212 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x0212 }
        r18 = TAG;	 Catch:{ all -> 0x0212 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r15);	 Catch:{ all -> 0x0212 }
        if (r10 == 0) goto L_0x01f5;
    L_0x01f2:
        r10.close();	 Catch:{ IOException -> 0x01ff }
    L_0x01f5:
        if (r8 == 0) goto L_0x0236;
    L_0x01f7:
        r11 = 0;
        if (r4 == 0) goto L_0x0008;
    L_0x01fa:
        r4.disconnect();
        goto L_0x0008;
    L_0x01ff:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x01f5;
    L_0x0212:
        r17 = move-exception;
        if (r10 == 0) goto L_0x0218;
    L_0x0215:
        r10.close();	 Catch:{ IOException -> 0x0222 }
    L_0x0218:
        if (r8 == 0) goto L_0x0235;
    L_0x021a:
        r11 = 0;
        if (r4 == 0) goto L_0x0008;
    L_0x021d:
        r4.disconnect();
        goto L_0x0008;
    L_0x0222:
        r5 = move-exception;
        r18 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r18 = r18.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r19 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r18;
        r1 = r19;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x0218;
    L_0x0235:
        throw r17;	 Catch:{ all -> 0x01a1 }
    L_0x0236:
        r14 = 0;
        r14 = r4.getResponseCode();	 Catch:{ IOException -> 0x0286 }
    L_0x023b:
        r12 = 0;
        r17 = 400; // 0x190 float:5.6E-43 double:1.976E-321;
        r0 = r17;
        if (r14 < r0) goto L_0x02c0;
    L_0x0242:
        r12 = r4.getErrorStream();	 Catch:{ IOException -> 0x02df }
    L_0x0246:
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x02df }
        r11 = r17.createResponse();	 Catch:{ IOException -> 0x02df }
        r11.setStatusCode(r14);	 Catch:{ IOException -> 0x02df }
        r17 = r22.getRequest();	 Catch:{ IOException -> 0x02df }
        r17 = r17.getUrl();	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r11.setUrl(r0);	 Catch:{ IOException -> 0x02df }
        r17 = "ETag";
        r0 = r17;
        r17 = r4.getHeaderField(r0);	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r11.setEtag(r0);	 Catch:{ IOException -> 0x02df }
        if (r12 == 0) goto L_0x0350;
    L_0x026e:
        r17 = convertStreamToString(r12);	 Catch:{ NullPointerException -> 0x02c5, JsonSyntaxException -> 0x02ff, IllegalStateException -> 0x0321 }
        r0 = r17;
        r11.parse(r0);	 Catch:{ NullPointerException -> 0x02c5, JsonSyntaxException -> 0x02ff, IllegalStateException -> 0x0321 }
    L_0x0277:
        r12.close();	 Catch:{ IOException -> 0x033c }
    L_0x027a:
        if (r12 == 0) goto L_0x027f;
    L_0x027c:
        r12.close();	 Catch:{ IOException -> 0x0359 }
    L_0x027f:
        if (r4 == 0) goto L_0x0008;
    L_0x0281:
        r4.disconnect();
        goto L_0x0008;
    L_0x0286:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        r14 = r4.getResponseCode();	 Catch:{ IOException -> 0x029d }
        goto L_0x023b;
    L_0x029d:
        r7 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r7);	 Catch:{ all -> 0x01a1 }
        r11 = new com.tado.android.client.APICall$1;	 Catch:{ all -> 0x01a1 }
        r0 = r22;
        r11.<init>();	 Catch:{ all -> 0x01a1 }
        r11.setStatusCode(r14);	 Catch:{ all -> 0x01a1 }
        if (r4 == 0) goto L_0x0008;
    L_0x02bb:
        r4.disconnect();
        goto L_0x0008;
    L_0x02c0:
        r12 = r4.getInputStream();	 Catch:{ IOException -> 0x02df }
        goto L_0x0246;
    L_0x02c5:
        r5 = move-exception;
        r17 = 0;
        r0 = r17;
        r11.setSuccess(r0);	 Catch:{ IOException -> 0x02df }
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x02df }
        r17 = r17.toCrashlytics();	 Catch:{ IOException -> 0x02df }
        r18 = TAG;	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ IOException -> 0x02df }
        goto L_0x0277;
    L_0x02df:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x031a }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x031a }
        r18 = TAG;	 Catch:{ all -> 0x031a }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x031a }
        r8 = 1;
        if (r12 == 0) goto L_0x02f7;
    L_0x02f4:
        r12.close();	 Catch:{ IOException -> 0x036d }
    L_0x02f7:
        if (r4 == 0) goto L_0x02fc;
    L_0x02f9:
        r4.disconnect();
    L_0x02fc:
        r11 = 0;
        goto L_0x0008;
    L_0x02ff:
        r5 = move-exception;
        r17 = 0;
        r0 = r17;
        r11.setSuccess(r0);	 Catch:{ IOException -> 0x02df }
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x02df }
        r17 = r17.toCrashlytics();	 Catch:{ IOException -> 0x02df }
        r18 = TAG;	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ IOException -> 0x02df }
        goto L_0x0277;
    L_0x031a:
        r17 = move-exception;
        if (r12 == 0) goto L_0x0320;
    L_0x031d:
        r12.close();	 Catch:{ IOException -> 0x0381 }
    L_0x0320:
        throw r17;	 Catch:{ all -> 0x01a1 }
    L_0x0321:
        r5 = move-exception;
        r17 = 0;
        r0 = r17;
        r11.setSuccess(r0);	 Catch:{ IOException -> 0x02df }
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x02df }
        r17 = r17.toCrashlytics();	 Catch:{ IOException -> 0x02df }
        r18 = TAG;	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ IOException -> 0x02df }
        goto L_0x0277;
    L_0x033c:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ IOException -> 0x02df }
        r17 = r17.toCrashlytics();	 Catch:{ IOException -> 0x02df }
        r18 = TAG;	 Catch:{ IOException -> 0x02df }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ IOException -> 0x02df }
        goto L_0x027a;
    L_0x0350:
        r17 = 0;
        r0 = r17;
        r11.setSuccess(r0);	 Catch:{ IOException -> 0x02df }
        goto L_0x027a;
    L_0x0359:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x027f;
    L_0x036d:
        r5 = move-exception;
        r17 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r17 = r17.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r18 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r17;
        r1 = r18;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x02f7;
    L_0x0381:
        r5 = move-exception;
        r18 = com.tado.android.utils.Snitcher.start();	 Catch:{ all -> 0x01a1 }
        r18 = r18.toCrashlytics();	 Catch:{ all -> 0x01a1 }
        r19 = TAG;	 Catch:{ all -> 0x01a1 }
        r0 = r18;
        r1 = r19;
        r0.logException(r1, r5);	 Catch:{ all -> 0x01a1 }
        goto L_0x0320;
        */
        throw new UnsupportedOperationException("Method not decompiled: com.tado.android.client.APICall.doInBackground(java.lang.Void[]):com.tado.android.responses.Response");
    }

    protected void onPostExecute(Response result) {
        if (this.mContext.get() == null || !((Activity) this.mContext.get()).isFinishing()) {
            dismiss();
            if (this.mListener == null) {
                clean();
                return;
            }
            if (result == null) {
                this.mListener.onCallFailed(this, result);
            } else if (result.getStatusCode() < Callback.DEFAULT_DRAG_ANIMATION_DURATION || result.getStatusCode() >= 300) {
                if (result.getStatusCode() == 401) {
                    Snitcher.start().toCrashlytics().log(4, TAG, "401 Logout from APICall " + result.getUrl(), new Object[0]);
                    logoutUser();
                    this.mListener.onCallFailed(this, result);
                } else if (result.getStatusCode() == 410) {
                    Snitcher.start().toCrashlytics().log(4, TAG, "410 from APICall " + result.getUrl(), new Object[0]);
                    showForceUpdate();
                    this.mListener.onCallFailed(this, result);
                } else {
                    this.mListener.onCallFailed(this, result);
                }
            } else if (result.isSuccess()) {
                this.mListener.onProcessResponse(this, result);
            } else {
                this.mListener.onCallFailed(this, result);
            }
            clean();
        }
    }

    public void setRequest(Request mRequest) {
        this.mRequest = mRequest;
    }

    protected void onCancelled() {
        super.onCancelled();
        this.mListener.onCallFailed(this, null);
        clean();
    }

    static String convertStreamToString(InputStream is) {
        String result = "";
        Scanner s = null;
        try {
            s = new Scanner(is).useDelimiter("\\A");
            result = s.hasNext() ? s.next() : "";
            if (s != null) {
                s.close();
            }
        } catch (Exception e) {
            Snitcher.start().toCrashlytics().logException(TAG, e);
            if (s != null) {
                s.close();
            }
        } catch (Throwable th) {
            if (s != null) {
                s.close();
            }
        }
        return result;
    }

    private void logoutUser() {
        Util.clearUserDataOnLogin();
        TadoApplication.locationManager.stopTracking();
        if (this.mContext.get() == null || !(this.mContext.get() instanceof LoginActivity)) {
            startActivity(PromoActivity.class, 268435456);
        }
    }

    private void showForceUpdate() {
        if (this.mContext.get() != null) {
            NavigationController.showForceUpdateScreen((Context) this.mContext.get());
        }
    }

    private void startActivity(Class clazz, int... intentFlags) {
        if (this.mContext.get() != null) {
            Intent intent = new Intent((Context) this.mContext.get(), clazz);
            for (int addFlags : intentFlags) {
                intent.addFlags(addFlags);
            }
            ((Activity) this.mContext.get()).startActivity(intent);
            ((Activity) this.mContext.get()).finish();
        }
    }

    public boolean ismShowLoaderDialog() {
        return this.mShowLoaderDialog;
    }

    public void setmShowLoaderDialog(boolean mShowLoaderDialog) {
        this.mShowLoaderDialog = mShowLoaderDialog;
    }

    public void dismiss() {
        if (this.mProgress != null && ((ProgressDialog) this.mProgress.get()).isShowing()) {
            ((ProgressDialog) this.mProgress.get()).dismiss();
        }
    }

    private void clean() {
        if (this.mProgress != null) {
            ((ProgressDialog) this.mProgress.get()).dismiss();
        }
        this.mProgress = null;
        this.mContext = null;
        this.mListener = null;
        cancel(true);
    }
}
