package eu.hlavki.netbeans.svuid;

import java.util.Collections;
import java.util.Set;
import javax.lang.model.element.Modifier;
import javax.lang.model.element.Name;

public abstract class ElementInfo implements Comparable<ElementInfo>
{
    protected String    name;
    protected int       access;
    protected String    descriptor;

    public ElementInfo( Name name, Set<Modifier> modifiers, String descriptor )
    {
        this.name       = new StringBuilder( name ).toString();
        this.access     = getAccessFlag( modifiers );
        this.descriptor = descriptor;
    }

    public ElementInfo( Name name, String description )
    {
        this( name, Collections.<Modifier>emptySet(), description );
    }

    public int getAccess()
    {
        return this.access;
    }

    public String getDescriptor()
    {
        return this.descriptor;
    }

    public String getName()
    {
        return this.name;
    }

    public boolean includeInSerialVersionUID()
    {
        return true;
    }

    protected final int getAccessFlag( Set<Modifier> modifiers )
    {
        int accessFlag = 0;

        for ( Modifier modifier : modifiers ) {
            accessFlag |= toModifier( modifier );
        }

        return accessFlag;
    }

    @Override
    public String toString()
    {
        return this.name + "|" + this.descriptor + "|" + getSvuidAccess();
    }

    @Override
    public int compareTo( ElementInfo o )
    {
        return this.getSortingName().compareTo( o.getSortingName() );
    }

    public abstract int getSvuidAccess();

    public abstract String getSortingName();

    private int toModifier( Modifier modifier )
    {
        return
            switch ( modifier ) {
                case ABSTRACT     -> { yield java.lang.reflect.Modifier.ABSTRACT;     }
                case FINAL        -> { yield java.lang.reflect.Modifier.FINAL;        }
                case NATIVE       -> { yield java.lang.reflect.Modifier.NATIVE;       }
                case PRIVATE      -> { yield java.lang.reflect.Modifier.PRIVATE;      }
                case PROTECTED    -> { yield java.lang.reflect.Modifier.PROTECTED;    }
                case PUBLIC       -> { yield java.lang.reflect.Modifier.PUBLIC;       }
                case STATIC       -> { yield java.lang.reflect.Modifier.STATIC;       }
                case STRICTFP     -> { yield java.lang.reflect.Modifier.STRICT;       }
                case SYNCHRONIZED -> { yield java.lang.reflect.Modifier.SYNCHRONIZED; }
                case TRANSIENT    -> { yield java.lang.reflect.Modifier.TRANSIENT;    }
                case VOLATILE     -> { yield java.lang.reflect.Modifier.VOLATILE;     }
                case DEFAULT, NON_SEALED, SEALED -> { yield 0; }
            };
    }
}
