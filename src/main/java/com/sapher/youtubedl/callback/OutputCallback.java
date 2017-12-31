package com.sapher.youtubedl.callback;

public interface OutputCallback {
	void onStdOutLine(String stdOutLine);
	void onStdErrLine(String stdErrLine);
}
