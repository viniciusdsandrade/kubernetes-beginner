package yaml

import org.yaml.snakeyaml.Yaml
import java.io.File
import org.yaml.snakeyaml.DumperOptions
import org.yaml.snakeyaml.DumperOptions.FlowStyle.BLOCK

fun main() {
    // 1. Configure as opções de formatação
    val options = DumperOptions().apply {
        defaultFlowStyle = BLOCK
        isPrettyFlow = true
        indent = 2
        width = 80
    }

    // 2. Instancia o parser/dumper com essas opções
    val yaml = Yaml(options)

    // 3. Carrega o YAML num objeto genérico (Map<String, Any>)
    val data: Any = File("cars.yaml")
        .inputStream()
        .use { yaml.load(it) }

    // 4. Desserializa de volta para uma string YAML bem formatada
    val prettyYaml: String = yaml.dump(data)

    // 5. Imprime o YAML no mesmo estilo legível do arquivo original
    println(prettyYaml)
}