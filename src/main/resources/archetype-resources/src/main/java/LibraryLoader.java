package ${package};

import io.papermc.paper.plugin.loader.PluginClasspathBuilder;
import io.papermc.paper.plugin.loader.PluginLoader;
import io.papermc.paper.plugin.loader.library.impl.MavenLibraryResolver;
import org.eclipse.aether.artifact.DefaultArtifact;
import org.eclipse.aether.graph.Dependency;
import org.eclipse.aether.repository.RemoteRepository;
import org.jetbrains.annotations.NotNull;

@SuppressWarnings({"UnstableApiUsage", "unused"})
public class LibraryLoader implements PluginLoader
{
    @Override
    public void classloader(@NotNull PluginClasspathBuilder classpathBuilder)
    {
        MavenLibraryResolver resolver = new MavenLibraryResolver();
        resolver.addDependency(new Dependency(new DefaultArtifact("com.zaxxer:HikariCP:5.1.0"), null));
        resolver.addDependency(new Dependency(new DefaultArtifact("org.mariadb.jdbc:mariadb-java-client:3.3.2"), null));
        resolver.addRepository(new RemoteRepository.Builder("maven", "default", "https://repo.maven.apache.org/maven2").build());
        classpathBuilder.addLibrary(resolver);
    }
}