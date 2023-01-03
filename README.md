# AWS CDK Template
An AWS CDK template that can be used to deploy with CloudFormation/CDK to AWS,
creating a repo of itself ready for future development. This should be a
starting point for any new AWS work you start as currently CDK doesn't have
great support for adopting resources deployed with the AWS console.

Starting with this template, you manage the related cloud infrastructure and
app through the same repo with a full CloudFormation history. For example:
- Start with this template.
- Create and deploy an app with API gateway with Lambdas.
- Add a CD/CI pipeline to automate the deployment using CodeCommit, CodeBuild, CodeArtifact/ECR/S3, and CodePipeline.
- Run out of money and just tear the whole thing down with cdk destroy.

## Notice
The motivation behind this repo is to show an example on how to manage
Infrastructure as code (IAC) with AWS. Especially for greenfield projects,
it is important to have resoures managed with a stack, with solid ID naming
schemes and whatnot.

### Disclaimer
This is a dummified stripped down version of my personal CDK library. It is
meant as a customized starting point to speed up getting started, not as-is
code to deploy. If you don't know basics, can't figure out constructs from
the AWS CDK docs, and what not, this is probably not for you.

Also, it is in Java. Simply for the fact that Java is the best language CDK is availalble in. If only you didn't need npm to install the CDK CLI life would
be good.

## Instructions
This is a dummy template you can use as a starting point for AWS development.
When run, the template creates a CDK stack with a CodeCommit repo on branch
dev, with itself (minus files in .gitignore) as the initial commit. You can
then fetch the repo from CodeCommit, and any future development will deploy to
the same stack. You can also easily tear the stack down, and also maintain a
history to easily roll back to a working stack.

### Requirements
1. You need an AWS account with enough permissions to deploy everything you
need. So likely an admin account (note: admin account != root account).
2. You also need to install CDK. AWS has instructions for this.
3. Since it is in java, you need a Java SDK set up. AWS Corretto or any other
of the free implementations works.
4. You might want to update the gradle wrapper if it is old. Same with any
dependencies in build.gradle.kts.

### What to do before first deploy
First, after cloning this repo, delete the (hidden) .git directory since you
will be committing to a new repo, and I think having the .git there might mess
stuff up.

Next, you want to at least change the following:
- under CdkBaseApp.java, change .account and .region to yours, or comment
them out to use other methods to provide the account and region (AWS docs
cover this, e.g. env variables and whatnot).

You also likely want to change the "BaseApp" strings in the following:
- settings.gradle.kts
- /app/build.gradle.kts

Same goes with cdk-base-app string inside settings.gradle.kts. You might also
want to modify Java versions, add plugins and whatnot in the /app/build.gradle.kts. It's basic gradle stuff overall.

Another likely thing you want to change is to:
- Rename /app/src/main/java/com.tbd and all the files and references to these.
- Rename the ids in CdkBaseApp, BaseAppStack, and CodeCommitService .java files

This should be a decent starting point, though you can obviously work
locally to create a fuller stack, add functionality like lambdas, add a
CD/CI pipeline in CDK or whatever you please.

### First deploy
Deploying is as simple as cdk deploy. If you're like me, you might need to
do cdk --profile myprofile deploy. If you did a lot of modifications, you
might want to synth separately first, but as that is part of the deploy, no
harm not doing it.

During the deploy, you will likely get some prompt for the changes that will
happen. Review them, it is mostly pretty standard IAM permissions you would
do on the AWS Console if deploying manually most of the time. If satisfied
enter y.

### After first deploy
Once the deploy is successful, you can review it in the AWS Console. Head to
the CodeBuild section to get the repo url, and see the instructions there
for getting your CodeCommit keys to download the repo. Or use HTTPS and login
to your account with git when pulling.

However, before you do that, delete the local first deploy, especially if
you have the repo in the same place as where you'd pull from CodeCommit.

Once you have pulled from CodeCommit, you have the exact same setup, except
it is under version control. Now you're ready to develop your cloud
infrastructure and applications with a full CDK stack history and all
resources under it.

