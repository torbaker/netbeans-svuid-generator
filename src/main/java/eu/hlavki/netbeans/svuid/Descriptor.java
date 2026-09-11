package eu.hlavki.netbeans.svuid;

import java.util.List;
import java.util.logging.Logger;
import javax.lang.model.element.Name;
import javax.lang.model.element.TypeElement;
import javax.lang.model.type.ArrayType;
import javax.lang.model.type.DeclaredType;
import javax.lang.model.type.ExecutableType;
import javax.lang.model.type.TypeMirror;
import javax.lang.model.type.TypeVariable;

public class Descriptor
{
    private static final Logger LOG = Logger.getLogger( Descriptor.class.getName() );

    /**
     * Converts a class name into the internal representation used in the JVM.
     * <p>
     * <p>
     * Note that <code>toJvmName(toJvmName(s))</code> is equivalent to <code>toJvmName(s)</code>.
     */
    private static String toJvmName( String classname )
    {
        return classname.replace( '.', '/' );
    }

    /**
     * Converts a class name into the internal representation used in the JVM.
     * <p>
     * <p>
     * Note that <code>toJvmName(toJvmName(s))</code> is equivalent to <code>toJvmName(s)</code>.
     */
    private static StringBuffer toJvmName( StringBuffer sb, Name clazzName )
    {
        for ( int idx = 0; idx < clazzName.length(); idx++ ) {
            char ch = clazzName.charAt( idx );

            sb.append( (ch == '.') ? '/' : ch );
        }

        return sb;
    }

    public static String of( TypeMirror type )
    {
        StringBuffer sb = new StringBuffer();

        descriptor( sb, type );

        return sb.toString();
    }

    private static void descriptor( StringBuffer sb, TypeMirror type )
    {
        switch ( type.getKind() ) {
            case ARRAY -> {
                sb.append( "[" );

                descriptor( sb, ((ArrayType) type).getComponentType() );
            }
            case DECLARED -> {
                DeclaredType declaredType = (DeclaredType) type;
                TypeElement typeElement = (TypeElement) declaredType.asElement();
                sb.append( 'L' );
                toJvmName( sb, typeElement.getQualifiedName() );
//                List<? extends TypeMirror> typeArgs = declaredType.getTypeArguments();
//                if (!typeArgs.isEmpty()) {
//                    sb.append('<');
//                    for (TypeMirror typeArg : typeArgs) {
//                        descriptor(sb, typeArg);
//                    }
//                    sb.append('>');
//                }
                sb.append( ';' );
            }
            case TYPEVAR -> {
                TypeVariable typeVar = (TypeVariable) type;
                sb.append( 'T' ).append( typeVar.asElement().getSimpleName() ).append( ';' );
            }
            case INT -> {
                sb.append( 'I' );
            }
            case BYTE -> {
                sb.append( 'B' );
            }
            case LONG -> {
                sb.append( 'J' );
            }
            case DOUBLE -> {
                sb.append( 'D' );
            }
            case FLOAT -> {
                sb.append( 'F' );
            }
            case CHAR -> {
                sb.append( 'C' );
            }
            case SHORT -> {
                sb.append( 'S' );
            }
            case BOOLEAN -> {
                sb.append( 'Z' );
            }
            case VOID -> {
                sb.append( 'V' );
            }
            case EXECUTABLE -> {
                sb.append( '(' );
                ExecutableType execType = (ExecutableType) type;
                List<? extends TypeMirror> paramTypes = execType.getParameterTypes();

                for ( TypeMirror paramType : paramTypes ) {
                    descriptor( sb, paramType );
                }
                sb.append( ')' );
                descriptor( sb, execType.getReturnType() );
            }
            default -> {
                Descriptor.LOG.severe( "UNKNOWN TYPE: " + type.getKind() );
            }
        }
    }
}
