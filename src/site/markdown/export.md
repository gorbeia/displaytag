Exporting data
--------------

When you set the Table Tag's export attribute to "true", a footer will
appear below the table which will allow you to export the data being
shown in various formats.

Displaytag includes a few ready made export views which allow you to
export data in CSV, excel, and XML format. A simple PDF export view is
also available. The following table lists the predefined export options
included in displaytag distribution.

  ------------------------ ------------------------ ------------------------
  Media                    CSV                      Excel
  Export View Class        org.displaytag.export.Cs org.displaytag.export.Ex
  Description              vView                    celView
                           Export to comma          Export to excel - ascii
                           separated list           format, tab separated
  ------------------------ ------------------------ ------------------------

### Configuring export and export views

The export.types parameter contains the list of registered export views.
For each export type you can configure other parameters: see the export.
exportname.\* parameters in [configuration](configuration.html).

You can enable/disable a specific export type using the export.
exportname.enabled parameter.

If you don't want some column to show during export (or you only want
them to show during export) you can use the column media attribute (see
tag reference for more details).

### Adding a new Export view

1\. Write your own exportView class. You need to implement the
org.displaytag.export.TextExportView or
org.displaytag.export.BinaryExportView interface. You can look at the
[sample binary PDF export
view](#displaytagxreforgdisplaytagexportPdfView.html) or to [the base
text export view](#displaytagxreforgdisplaytagexportBaseExportView.html)
used by displaytag.

2\. Add a displaytag.properties file in your application classpath (if
you already don't have one) and add the name of your export media along
with the default ones to the export type parameter:
`export.types=csv excel xml [mymedia]`

3\. Always in displaytag.properties , add the following properties:

    export.[mymedia]=true

    export.[mymedia].class=fully.qualified.class.name

    export.[mymedia].label=Click here to try my export

    # include header parameter is forwarded to your export view
    export.[mymedia].include_header=true

    # if set, file is downloaded instead of opened in the browser window
    export.[mymedia].filename=

4\. Try it. You should see a new link with the text you added to export.
mymedia .label . Clicking on it will invoke your Export view. You should
see the results in your browser.

### Text/Binary export views

Common displaytag export options (CSV, Xml and Excel) output a simple
text-based format. Other file formats require binary content, like the
sample PDF included with the distribution.

Exporting binary data from a JSP is a bit tricky, since JSPs are only
designed to output characters: as a starting point keep in mind that
binary export is not assured to work on every application server , at
least without the help of an external filter (see [export
filter](#export_filter.html)).

It may work without a filter if your application server allows JSPs to
call response.getOutputStream() , but this method really shouldn't be
used in JSPs. Using an the export filter, especially in buffered mode,
could solve the problem, since the output stream is requested by the
filter outside the JSP.

