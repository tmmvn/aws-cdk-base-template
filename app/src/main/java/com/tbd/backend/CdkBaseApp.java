package com.tbd.backend;

import com.tbd.stacks.BaseAppStack;
import software.amazon.awscdk.App;
import software.amazon.awscdk.Environment;
import software.amazon.awscdk.StackProps;

public class CdkBaseApp
  {
    public static void main(final String[] args)
      {
        App app = new App();
        new BaseAppStack(app,
          "CdkBaseAppStack",
          StackProps.builder()
            .env(Environment.builder()
              .account("youraccountid")
              .region("yourregion")
              .build())
            .build());
        app.synth();
      }
  }
