/*
 * $Id: Struct.java,v 1.6 2004/09/21 03:42:41 pspeed Exp $
 *
 * Copyright (c) 2004, Paul Speed
 * All rights reserved.
 *
 * Redistribution and use in source and binary forms, with or without
 * modification, are permitted provided that the following conditions
 * are met:
 *
 * 1) Redistributions of source code must retain the above copyright notice,
 *    this list of conditions and the following disclaimer.
 * 2) Redistributions in binary form must reproduce the above copyright
 *    notice, this list of conditions and the following disclaimer in the
 *    documentation and/or other materials provided with the distribution.
 * 3) Neither the names "Progeeks", "Meta-JB", nor the names of its contributors
 *    may be used to endorse or promote products derived from this software
 *    without specific prior written permission.
 *
 * THIS SOFTWARE IS PROVIDED BY THE COPYRIGHT HOLDERS AND CONTRIBUTORS "AS IS"
 * AND ANY EXPRESS OR IMPLIED WARRANTIES, INCLUDING, BUT NOT LIMITED TO, THE
 * IMPLIED WARRANTIES OF MERCHANTABILITY AND FITNESS FOR A PARTICULAR PURPOSE
 * ARE DISCLAIMED. IN NO EVENT SHALL THE COPYRIGHT OWNER OR CONTRIBUTORS BE
 * LIABLE FOR ANY DIRECT, INDIRECT, INCIDENTAL, SPECIAL, EXEMPLARY, OR
 * CONSEQUENTIAL DAMAGES (INCLUDING, BUT NOT LIMITED TO, PROCUREMENT OF
 * SUBSTITUTE GOODS OR SERVICES; LOSS OF USE, DATA, OR PROFITS; OR BUSINESS
 * INTERRUPTION) HOWEVER CAUSED AND ON ANY THEORY OF LIABILITY, WHETHER IN
 * CONTRACT, STRICT LIABILITY, OR TORT (INCLUDING NEGLIGENCE OR OTHERWISE)
 * ARISING IN ANY WAY OUT OF THE USE OF THIS SOFTWARE, EVEN IF ADVISED OF THE
 * POSSIBILITY OF SUCH DAMAGE.
 */

package org.progeeks.nwn.gff;

import java.util.*;

/**
 *  Contains a list of elements accessible by name.
 *
 *  @version   $Revision: 1.6 $
 *  @author    Paul Speed
 */
public class Struct
{
    private int id;
    private Map elements = new LinkedHashMap();

    public Struct( int id )
    {
        this.id = id;
    }

    public Struct( int id, List values )
    {
        this.id = id;
        setValues( values );
    }

    public int getId()
    {
        return( id );
    }

    public void setValues( List values )
    {
        elements.clear();
        if( values == null )
            return;
        for( Iterator i = values.iterator(); i.hasNext(); )
            {
            Element e = (Element)i.next();
            elements.put( e.getName(), e );
            }
    }

    public Iterator values()
    {
        return( elements.values().iterator() );
    }

    public List getValues()
    {
        return( new ArrayList( elements.values() ) );
    }

    public void addValue( Element element )
    {
        setValue( element.getName(), element );
    }

    public void setValue( String name, Element element )
    {
        elements.put( name, element );
    }

    public Element getValue( String name )
    {
        return( (Element)elements.get(name) );
    }

    public int getInt( String name )
    {
        Element el = getValue( name );
        if( el instanceof IntElement )
            return( ((IntElement)el).getValue() );
        return( -1 );
    }

    public String getString( String name )
    {
        Element el = getValue( name );
        if( el instanceof StringElement )
            return( ((StringElement)el).getValue() );
        else if( el instanceof LocalizedStringElement )
            return( ((LocalizedStringElement)el).getValue() );
        return( null );
    }

    public List getList( String name )
    {
        Element el = getValue( name );
        if( el instanceof ListElement )
            return( ((ListElement)el).getValue() );
        return( null );
    }

    public int hashCode()
    {
        return( id + elements.hashCode() );
    }

    public boolean equals( Object obj )
    {
        if( obj == null )
            return( false );

        if( !getClass().equals( obj.getClass() ) )
            return( false );

        Struct s = (Struct)obj;

        if( id != s.id )
            return( false );

        return( elements == s.elements || (elements != null && elements.equals( s.elements )) );
    }

    public String toString()
    {
        return( "Struct[" + elements + "]" );
    }
}
