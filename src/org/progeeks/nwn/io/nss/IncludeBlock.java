/*
 * $Id: IncludeBlock.java,v 1.1 2004/10/03 22:46:28 pspeed Exp $
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
 * 3) Neither the names "Progeeks", "NWN Tools", nor the names of its contributors
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

package org.progeeks.nwn.io.nss;


/**
 *  Represents a block of script code containing an include directive.
 *
 *  @version   $Revision: 1.1 $
 *  @author    Paul Speed
 */
public class IncludeBlock extends ScriptBlock
{
    private String script;

    public IncludeBlock( StringBuffer block )
    {
        super( block );
        setType( INCLUDE );
    }

    public String getName()
    {
        return( script );
    }

    public void append( String text )
    {
        super.append( text );

        if( text.trim().length() == 0 )
            {
            return;
            }

        if( script == null )
            script = text;
        else
            throw new RuntimeException( "Format error parsing constant declaration." );
    }

    public void setType( int type )
    {
        if( type != INCLUDE )
            throw new IllegalArgumentException( "IncludeBlock must be of type constant." );
        super.setType( type );
    }

    public String toString()
    {
        StringBuffer sb = new StringBuffer();
        sb.append( "IncludeBlock[" );
        sb.append( script );
        sb.append( "] (" + getStartLine() + " -> " + getEndLine() + ") = " );
        sb.append( getBlockText() );

        return( sb.toString() );
    }
}
