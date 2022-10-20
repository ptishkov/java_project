package github;

import com.google.common.collect.ImmutableMap;
import com.jcabi.github.*;
import jdk.nashorn.internal.ir.annotations.Immutable;
import org.testng.annotations.Test;

import java.io.IOException;

public class GithubTests {
    @Test
    public void testCommits() throws IOException {
        Github github = new RtGithub("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");
        RepoCommits commits = github.repos().get(new Coordinates.Simple("ptishkov", "java_project")).commits();
        for (RepoCommit commit : commits.iterate(new ImmutableMap.Builder<String, String>().build())) {
            System.out.println(new RepoCommit.Smart(commit).message());
        }
    }
}
