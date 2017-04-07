import com.sysgears.theme.ResourceMapper
import com.sysgears.theme.deploy.GHPagesDeployer
import com.sysgears.theme.taglib.ThemeTagLib


/**
 * This setting defines the character encoding of html pages, and therefore should match
 * the character encoding of the site files on a filesystem
 */
html_encoding = 'utf-8'

/**
 * Resource mapper and tag libs.
 */
resource_mapper = new ResourceMapper(site).map
tag_libs = [ThemeTagLib]

/**
 * Theme features management.
 */
features {
    /**
     * Defines the highlighting feature. Accepts the following values:
     *  - none - code highlighting is disabled for the theme.
     *  - pygments - code highlighting is enabled and provided by Python Pygments.
     */
    highlight = 'none'

    /**
     * Defines Compass behavior. This property accepts the following values:
     * - auto, ruby, jruby - Default value. For any of these values the specified Ruby interpreter (ruby.interpreter
     *                       config value)  is used. Otherwise, if no interpreter is defined, falls back to JRuby.
     * - shell - Uses command shell to execute compass.
     * - none - compass is disabled.
     */
    compass = 'none'

    /**
     * Defines the tool for Markdown documents processing. Accepts the following values:
     * - txtmark - default value. This way TxtMark is used for markdown processing.
     * - pegdown - Use Pegdown for markdown documents processing.
     */
    markdown = 'txtmark'
}

/**
 * Defines the set of variables, appended to the 'site' global variable, depending on environment that is used.
 */
environments {

    /**
     * Dev configuration.
     */
    dev {
        log.info 'Development environment is used'

        /**
         * Base URL for the site. If the generate_absolute_links config value is set to "true", this value will be
         * automatically prepended to any asset path of the theme.
         */
        url = "http://localhost:${jetty_port}"

        /**
         * Should posts with "published = false" be processed.
         */
        show_unpublished = true
    }

    /**
     * Prod configuration.
     */
    prod {
        log.info 'Production environment is used'

        /**
         * Base URL for the site. If the generate_absolute_links config value is set to "true", this value will be
         * automatically prepended to any asset path of the theme.
         */
        url = '.'

        /**
         * Should posts with "published = false" be processed.
         */
        show_unpublished = false

        /**
         * List of features configurations.
         */
        features {
            minify_xml = false
            minify_html = false
            minify_js = false
            minify_css = false
        }
    }

    /**
     * Theme-specific command-mode environment, used when running a custom command defined in SiteConfig.groovy
     */
    cmd {
        features {
            compass = 'none'
            highlight = 'none'
        }
    }
}

/**
 * Python RPC configuration
 */
python {

    /**
     * An interpreter that is used for executing Python scripts (e.g. for Python Pygments). This property accepts the following values:
     * - python - Uses Python that is installed on your system.
     * - jython - uses Jython integrated in Grain.
     * - auto - Default value. Uses Python that is installed on your system. If its not available, then falls back to Jython.
     */
    interpreter = 'jython'

    /**
     * If native system python distribution is used, then this value defines the paths to python executables. If any of
     * these fails, then the attempt to use next one takes place.
     */
    //cmd_candidates = ['python2', 'python', 'python2.7']

    /**
     * Forces the specific version of Python Setuptools.
     */
    //setup_tools = '2.1'
}

/**
 * Ruby RPC configuration
 */
ruby {

    /**
     * An interpreter that is used used for executing Ruby scripts (e.g. for AsciiDoc and Compass). This property accepts
     * the following values:
     * - ruby - uses Ruby that is installed on your system.
     * - jruby - uses jRuby integrated in Grain.
     * - auto - Default value. Uses Ruby that is installed on your system. If its not available, then falls back to JRuby.
     */
    interpreter = 'jruby'

    /**
     * If native system Ruby distribution is used, then this value defines the paths to Ruby executables. If any of these fails,
     * then the attempt to use next one takes place.
     */
    //cmd_candidates = ['ruby', 'ruby1.8.7', 'ruby1.9.3', 'user.home/.rvm/bin/ruby']

    /**
     * Forces the specific version of Ruby Gems - a tool for managing ruby gems.
     */
    //ruby_gems = '2.2.2'
}

/**
 * Setting this variable to "true" enables prefixing resource relative location with the value
 * of the "site.url" variable.
 */
generate_absolute_links = false;

/**
 * Exclude *.psd files from processing during "generate" task.
 */
binary_files << /(?i).*\.psd/

/**
 * S3 Deployment configurations.
 *
 * @attr s3bucket - your s3 bucket name
 * @attr deploy_s3 - a command to deploy to Amazon S3.
 */
s3_bucket = '' // your S3 bucket name
deploy_s3 = "s3cmd sync --acl-public --reduced-redundancy ${destination_dir}/ s3://${s3_bucket}/"

/**
 * GitHubPages deployment configuration.
 * @attr gh_pages_url Path to GitHub repository in format git@github.com:{username}/{repo}.git
 * @attr deploy a command to deploy to GitHubPages.
 */
gh_pages_url = '' // path to GitHub repository in format git@github.com:{username}/{repo}.git
deploy = new GHPagesDeployer(site).deploy

/**
 * List of custom command-line commands.
 */
commands = [

/**
 * Creates new page.
 *
 * location - relative path to the new page, should start with the /, i.e. /pages/index.html.
 * pageTitle - new page title
 */
'create-page': { String location, String pageTitle ->
        file = new File(content_dir, location)
        file.parentFile.mkdirs()
        file.exists() || file.write("""---
layout: default
title: "${pageTitle}"
published: true
---
""")}
]