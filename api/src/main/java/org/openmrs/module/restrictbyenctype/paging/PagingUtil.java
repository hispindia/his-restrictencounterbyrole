/**
 *  Copyright 2011 Health Information Systems Project of India
 *
 *  This file is part of RestrictEncounterByRole module.
 *
 *  RestrictEncounterByRole module is free software: you can redistribute it and/or modify
 *  it under the terms of the GNU General Public License as published by
 *  the Free Software Foundation, either version 3 of the License, or
 *  (at your option) any later version.

 *  RestrictEncounterByRole module is distributed in the hope that it will be useful,
 *  but WITHOUT ANY WARRANTY; without even the implied warranty of
 *  MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the
 *  GNU General Public License for more details.
 *
 *  You should have received a copy of the GNU General Public License
 *  along with RestrictEncounterByRole module.  If not, see <http://www.gnu.org/licenses/>.
 *
 **/

package org.openmrs.module.restrictbyenctype.paging;

public class PagingUtil
{
    public static final int DEFAULT_PAGE_SIZE = 20;

    private int currentPage;

    private int pageSize;

    private int total;

    private String link;
   
    public PagingUtil()
    {
    }

    public PagingUtil( String link, int pageSize )
    {
        currentPage = 1;
        this.pageSize = pageSize;
        total = 0;
        this.link = link;
    }

    public String getBaseLink()
    {
        if ( link.indexOf( "?" ) < 0 )
            return (new StringBuilder( String.valueOf( link ) )).append( "?" ).toString();
        else
            return (new StringBuilder( String.valueOf( link ) )).append( "&" ).toString();
    }

    public int getNumberOfPages()
    {
        if ( total % pageSize == 0 )
            return total / pageSize;
        else
            return total / pageSize + 1;
    }

    public int getStartPage()
    {
        int startPage = 1;
        if ( currentPage > 2 )
        {
            startPage = currentPage - 2;
            if ( getNumberOfPages() - startPage < 4 )
            {
                startPage = getNumberOfPages() - 4;
                if ( startPage <= 0 )
                    startPage = 1;
            }
        }
        return startPage;
    }

    public int getStartPos()
    {
        return currentPage <= 0 ? 0 : (currentPage - 1) * pageSize;
    }

    public int getEndPos()
    {
        int endPos = (getStartPos() + getPageSize()) - 1;
        endPos = endPos >= getTotal() ? getTotal() - 1 : endPos;
        return endPos;
    }

    public int getCurrentPage()
    {
        if ( currentPage > total )
            currentPage = total;
        return currentPage;
    }

    public void setCurrentPage( int currentPage )
    {
        if ( currentPage > 0 )
            this.currentPage = currentPage;
        else
            this.currentPage = 1;
    }

    public int getPageSize()
    {
        return pageSize;
    }

    public void setPageSize( int pageSize )
    {
        if ( pageSize > 0 )
            this.pageSize = pageSize;
        else
            this.pageSize = 50;
    }

    public int getTotal()
    {
        return total;
    }

    public void setTotal( int total )
    {
        this.total = total;
    }

    public String getLink()
    {
        return link;
    }

    public void setLink( String link )
    {
        this.link = link;
    }

}

