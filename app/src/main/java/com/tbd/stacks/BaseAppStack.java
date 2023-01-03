package com.tbd.stacks;

import com.tbd.services.CodeCommitService;
import software.amazon.awscdk.Stack;
import software.amazon.awscdk.StackProps;

public class BaseAppStack
  extends Stack
  {
    public BaseAppStack(final Construct scope, final String id)
      {
        this(scope, id, null);
      }

    public BaseAppStack(final Construct scope, final String id, final StackProps props)
      {
        super(scope, id, props);
        CodeCommitService codeCommit = new CodeCommitService(this,
          "cdk-base-app",
          "Base app.");
      }
  }
