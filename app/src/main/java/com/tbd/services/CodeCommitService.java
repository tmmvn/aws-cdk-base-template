package com.tbd.services;

import software.constructs.Construct;
import software.amazon.awscdk.services.codebuild.CodeCommitSourceProps;
import software.amazon.awscdk.services.codebuild.ISource;
import software.amazon.awscdk.services.codebuild.Source;
import software.amazon.awscdk.services.codecommit.Code;
import software.amazon.awscdk.services.codecommit.Repository;

/**
 A CodeCommit constructing service. Creates a CodeCommit service.
*/
public class CodeCommitService
  extends Construct
  {
    private final String id;
    private Repository repo;
    private ISource source;

    public CodeCommitService(Construct scope, String repositoryName, String description)
      {
        super(scope, "CodeCommit" + repositoryName);
        this.id = "CodeCommit" + repositoryName;
        CreateRepository(repositoryName, description);
      }

    private void CreateRepository(String repositoryName, String description)
      {
        repo = Repository.Builder.create(this, this.id + "Repository")
          .code(Code.fromZipFile("../initial/commit.zip", "dev"))
          .description(description)
          .repositoryName(repositoryName)
          .build();
        CodeCommitSourceProps props = CodeCommitSourceProps.builder()
          .branchOrRef("dev")
          .repository(repo)
          .build();
        source = Source.codeCommit(props);
      }

    public Repository GetRepository()
      {
        return repo;
      }
    public ISource GetSource() { return source; }
  }
