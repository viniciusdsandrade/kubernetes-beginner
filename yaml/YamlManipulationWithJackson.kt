package yaml

import com.fasterxml.jackson.core.util.DefaultIndenter
import com.fasterxml.jackson.databind.ObjectMapper
import com.fasterxml.jackson.dataformat.yaml.YAMLFactory
import com.fasterxml.jackson.module.kotlin.registerKotlinModule
import java.io.File
import com.fasterxml.jackson.databind.SerializationFeature.INDENT_OUTPUT
import com.fasterxml.jackson.core.util.DefaultPrettyPrinter
import com.fasterxml.jackson.core.util.DefaultIndenter.SYS_LF

fun main() {
    // 1. Configura o ObjectMapper para YAML e Kotlin
    val yamlMapper = ObjectMapper(YAMLFactory())
        .registerKotlinModule()
        .apply {
            // 2. Ativa indentação “bonitinha”
            enable(INDENT_OUTPUT)

            // 3. Ajusta o PrettyPrinter para blocos com 2 espaços
            setDefaultPrettyPrinter(
                DefaultPrettyPrinter().apply {
                    indentObjectsWith(DefaultIndenter("  ", SYS_LF))
                    indentArraysWith(DefaultIndenter("  ", SYS_LF))
                }
            )
        }

    // 4. Lê o YAML em um Map genérico
    @Suppress("UNCHECKED_CAST")
    val root = yamlMapper
        .readValue(File("cars.yaml"), Map::class.java) as Map<String, Any>

    // 5. Serializa de volta em YAML “bem formatado”
    val prettyYaml = yamlMapper.writeValueAsString(root)

    // 6. Imprime o YAML mantendo a estrutura e indentação
    println(prettyYaml)
}
