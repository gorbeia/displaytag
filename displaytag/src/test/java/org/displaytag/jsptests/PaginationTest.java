/**
 * Copyright (C) 2002-2014 Fabrizio Giustina, the Displaytag team
 *
 * Permission is hereby granted, free of charge, to any person obtaining a copy
 * of this software and associated documentation files (the "Software"), to deal
 * in the Software without restriction, including without limitation the rights
 * to use, copy, modify, merge, publish, distribute, sublicense, and/or sell
 * copies of the Software, and to permit persons to whom the Software is
 * furnished to do so, subject to the following conditions:
 *
 * The above copyright notice and this permission notice shall be included in
 * all copies or substantial portions of the Software.
 *
 * THE SOFTWARE IS PROVIDED "AS IS", WITHOUT WARRANTY OF ANY KIND, EXPRESS OR
 * IMPLIED, INCLUDING BUT NOT LIMITED TO THE WARRANTIES OF MERCHANTABILITY,
 * FITNESS FOR A PARTICULAR PURPOSE AND NONINFRINGEMENT. IN NO EVENT SHALL THE
 * AUTHORS OR COPYRIGHT HOLDERS BE LIABLE FOR ANY CLAIM, DAMAGES OR OTHER
 * LIABILITY, WHETHER IN AN ACTION OF CONTRACT, TORT OR OTHERWISE, ARISING FROM,
 * OUT OF OR IN CONNECTION WITH THE SOFTWARE OR THE USE OR OTHER DEALINGS IN
 * THE SOFTWARE.
 */
package org.displaytag.jsptests;

import org.apache.commons.lang3.StringUtils;
import org.displaytag.test.DisplaytagCase;
import org.junit.Assert;
import org.junit.Test;

import com.meterware.httpunit.GetMethodWebRequest;
import com.meterware.httpunit.WebLink;
import com.meterware.httpunit.WebRequest;
import com.meterware.httpunit.WebResponse;


/**
 * Basic tests for pagination.
 * @author Fabrizio Giustina
 * @version $Revision$ ($Author$)
 */
public class PaginationTest extends DisplaytagCase
{

    /**
     * @see org.displaytag.test.DisplaytagCase#getJspName()
     */
    public String getJspName()
    {
        return "pagination.jsp";
    }

    /**
     * Verifies that the generated page contains the pagination links with the inupt parameter. Tests #917200 ("{}" in
     * parameters).
     * @param jspName jsp name, with full path
     * @throws Exception any axception thrown during test.
     */
    @Override
    @Test
    public void doTest() throws Exception
    {

        WebRequest request = new GetMethodWebRequest(getJspUrl(getJspName()));
        request.setParameter("{foo}", "/.,;:/||\\bar");

        WebResponse response = this.runner.getResponse(request);

        if (this.log.isDebugEnabled())
        {
            this.log.debug("RESPONSE: " + response.getText());
        }

        WebLink[] links = response.getLinks();

        Assert.assertEquals("Wrong number of links in result.", 4, links.length);

        for (int j = 0; j < links.length; j++)
        {
            if (this.log.isDebugEnabled())
            {
                this.log.debug(j + " " + links[j].getURLString());
            }

            Assert.assertTrue(StringUtils.contains(links[j].getURLString(), "%7Bfoo%7D=%2F.%2C%3B%3A%2F%7C%7C%5Cbar"));
        }

    }
}