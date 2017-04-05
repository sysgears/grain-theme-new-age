import com.sysgears.theme.ResourceMapper
import com.sysgears.theme.deploy.GHPagesDeployer
import com.sysgears.theme.taglib.ThemeTagLib


/**
 * This setting defines the character encoding of html pages, and therefore should match
 * the character encoding of the site files on a filesystem
 */
html_encoding = 'utf-8' // it is passed to the mata charset attribute of the default page layout

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
     * Defines the highlighting feature. Accept the following values:
     *  - none - code highlighting is disabled for the theme.
     *  - pygments - code highlighting is enabled and achieved with Python Pygments.
     */
    highlight = 'none'

    /**
     * Defines Compass behavior. Compass is a Ruby gem, used by Grain for processing SASS/SCSS styles.
     * This property accepts the following values:
     * - auto, ruby, jruby - these do the same thing, actually. They all use the specified Ruby interpreter (or fall
     *                       back to Jruby, if no interpreter is defined) to install the Compass Gem and start the
     *                       Compass service for processing of SASS/SCSS.
     */
    compass = 'none'

    /**
     * Defines the way Markdown documents should be processed. Accepts the following values:
     * - txtmark - default value. This way TxtMark is used for
     *      markdown processing.
     * - pegdown - Use Pegdown for markdown documents processing.
     */
    markdown = 'txtmark'
}

/**
 * Defines the set of variables, appended to the 'site' global variable,
 * depending on environment that is used.
 */
environments {

    /**
     * Configurations, which is only available in dev mode.
     */
    dev {
        log.info 'Development environment is used'

        /**
         * Base URL for the site
         */
        url = "http://localhost:${jetty_port}"

        /**
         * Should posts with "published = false" be included in generated site sources.
         */
        show_unpublished = true
    }

    /**
     * Configurations, which only available in prod mode (for "generate" task).
     */
    prod {
        log.info 'Production environment is used'

        /**
         * Base URL for the site
         */
        url = '.' // site URL, for example http://www.example.com

        /**
         * Should posts with "published = false" be included in generated site sources.
         */
        show_unpublished = false

        /**
         * List of features configurations for production mode.
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
     * An interpreter that is used for executig Python scripts, since some pieces of functionality of the theme (e.g. Python Pygments) are
     * achieved through utilizing capabilities of related Ruby gems. This property accepts the following values:
     * - auto - Default value. Uses Python that is installed on your system. If its not available, then falls back to Jython.
     * - python - Uses Python that is installed on your system.
     * - jython - uses Jython integrated in Grain.
     */
    interpreter = 'jython' //

    /**
     * If native system python is used, then this value defines the paths to python executables. If any of these fails,
     * then the attempt to use next one takes place.
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
     * An interpreter that is used used for executing Ruby scripts, since some pieces of functionality of the theme (e.g. AsciiDoc and Compass) are
     * achieved through utilizing capabilities of related Ruby gems. This property accepts the following values:
     * - auto - Default value. Uses Ruby that is installed on your system. If its not available, then falls back to JRuby.
     * - ruby - uses Ruby that is installed on your system.
     * - jruby - uses jRuby integrated in Grain.
     */
    interpreter = 'jruby'

    /**
     * If native system Ruby is used, then this value defines the paths to Ruby executables. If any of these fails,
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
generate_absolute_links = true;

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