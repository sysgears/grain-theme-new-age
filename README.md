![Banner](https://raw.githubusercontent.com/sysgears/grain-theme-new-age/master/banner.jpg)

Grain Bootstrap Theme - New Age
===============================

[![Twitter Follow](https://img.shields.io/twitter/follow/sysgears.svg?style=social)](https://twitter.com/sysgears)

This one page [Grain] theme is based on Start Bootstrap [New Age Template].

Please, refer to the [theme page] and [documentation] for further details.

Contributing
============

Any person or company wanting to contribute to this project should follow
the following rules in order to their contribution being accepted.

Sign your Work
--------------

We require that all contributors "sign-off" on their commits.  This
certifies that the contribution is your original work, or you have rights to
submit it under the same license, or a compatible license.

Any contribution which contains commits that are not Signed-Off will not be
accepted.

To sign off on a commit you simply use the `--signoff` (or `-s`) option when
committing your changes:

    $ git commit -s -m "Adding a new widget driver for cogs."

This will append the following to your commit message:

    Signed-off-by: Your Name <your@email.com>

By doing this you certify the below:

    Developer's Certificate of Origin 1.1

If you wish to add the signoff to the commit message on your every commit
without the need to specify -s or --signoff, rename
.git/hooks/commit-msg.sample to .git/hooks/commit-msg and uncomment the lines:

``` sh
SOB=$(git var GIT_AUTHOR_IDENT | sed -n 's/^\(.*>\).*$/Signed-off-by: \1/p')
grep -qs "^$SOB" "$1" || echo "$SOB" >> "$1"
```

Developer's Certificate of Origin
---------------------------------

To help track the author of a patch as well as the submission chain,
and be clear that the developer has authority to submit a patch for
inclusion into this project please sign off your work.  The sign off
certifies the following:

    Developer's Certificate of Origin 1.1

    By making a contribution to this project, I certify that:

    (a) The contribution was created in whole or in part by me and I
        have the right to submit it under the open source license
        indicated in the file; or

    (b) The contribution is based upon previous work that, to the best
        of my knowledge, is covered under an appropriate open source
        license and I have the right under that license to submit that
        work with modifications, whether created in whole or in part
        by me, under the same open source license (unless I am
        permitted to submit under a different license), as indicated
        in the file; or

    (c) The contribution was provided directly to me by some other
        person who certified (a), (b) or (c) and I have not modified
        it.

    (d) I understand and agree that this project and the contribution
        are public and that a record of the contribution (including all
        personal information I submit with it, including my sign-off) is
        maintained indefinitely and may be redistributed consistent with
        this project or the open source license(s) involved.

    (e) I hereby grant to the project, SysGears, LLC and its successors; 
        and recipients of software distributed by the Project a perpetual,
        worldwide, non-exclusive, no-charge, royalty-free, irrevocable
        copyright license to reproduce, modify, prepare derivative works of,
        publicly display, publicly perform, sublicense, and distribute this
        contribution and such modifications and derivative works consistent
        with this Project, the open source license indicated in the previous
        work or other appropriate open source license specified by the Project
        and approved by the Open Source Initiative(OSI)
        at http://www.opensource.org.

License
=======

Grain Bootstrap Theme New Age is licensed under the terms of the
[Apache License, Version 2.0][Apache License, Version 2.0].

[Grain]: http://sysgears.com/grain/

[Apache License, Version 2.0]: http://www.apache.org/licenses/LICENSE-2.0.html
[Developer Certificate of Origin]: https://raw.github.com/sysgears/grain/master/DCO
[New Age Template]: http://startbootstrap.com/template-overviews/new-age/
[documentation]: http://sysgears.com/grain/docs/latest/
[theme page]: http://sysgears.com/grain/themes/new-age/
