Implicit objects created by table
---------------------------------

```html
<display:table name="test" id="testit">
    <display:column property="id" title="ID" />
    <display:column property="name" />
    <display:column title="static value">static</display:column>
    <display:column title="row number (testit_rowNum)"><%=pageContext.getAttribute("testit_rowNum")%></display:column>
    <display:column title="((ListObject)testit).getMoney()">
      <%=((ListObject)pageContext.getAttribute("testit")).getMoney()%>
    </display:column>
</display:table>
```

If you add and id attribute the table tag makes the object corresponding
to the given row available in the page context so you could use it
inside scriptlet code or some other tag. Another implicit object exposed
by the table tag is the row number, named id\_rowNum .

These objects are saved as attributes in the page scope (you can access
it using `pageContext.getAttribute("id")`). They are also defined as
nested variables (accessible using `<%=id%>` ), but only if the value of
the id atribute is not a runtime expression. The preferred way for
fetching the value is to always use pageContext.getAttribute().

If you do not specify the id attribute no object is added to the
pageContext by the table tag

This is a simple snippet which shows the use of the implicit objects
created by the table tag with JSTL.

```html
  <display table id="row" name="mylist">
    <display:column title="row number" >
      <c:out value="${row_rowNum}"/>
    </display:column>
    <display:column title="name" >
      <c:out value="${row.first_name}"/>
      <c:out value="${row.last_name}"/>
    </display:column>
  </display:table>
```
