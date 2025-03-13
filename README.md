[![Review Assignment Due Date](https://classroom.github.com/assets/deadline-readme-button-22041afd0340ce965d47ae6ef1cefeee28c7c493a6346c4f15d667ab976d596c.svg)](https://classroom.github.com/a/UUAiY6GZ)
# Template de App Android com Jetpack Compose

Este repositório serve como um template para projetos Android. Ele utiliza a SDK 35, Material Design 3 e Jetpack Compose, oferecendo uma base sólida para o desenvolvimento de aplicativos modernos.

## Como usar

2. **Clonar o repositório:**

   ```bash
   git clone <URL_DO_SEU_REPOSITORIO>
   ```

3. **Abrir no Android Studio:**

   Abra o projeto clonado no Android Studio.

4. **Configurar o projeto:**

   1. **Nome do Projeto e Pacote:**
      - Altere o nome do projeto no arquivo `settings.gradle.kts`.
      - Atualize o _namespace_ e o `applicationId` no arquivo `app/build.gradle.kts` para o pacote desejado. Essa mudança é crucial para a identidade do seu aplicativo.

   2. **Módulos:**
      - Verifique os módulos incluídos no projeto. Geralmente, você terá o módulo principal do aplicativo (":app"). Se necessário, adicione ou remova módulos.

   3. **Configuração de Build (`app/build.gradle.kts`):**
      - **`compileSdk`:** Define a versão do SDK de compilação. Use a versão mais recente estável, a menos que tenha requisitos específicos.
      - **`minSdk`:** Indica a versão mínima do Android suportada pelo seu aplicativo. Considere seu público-alvo ao definir este valor.
      - **`targetSdk`:** A versão do Android para a qual o aplicativo foi projetado. Recomenda-se usar a versão mais recente para aproveitar os recursos mais novos.
      - **`versionCode` e `versionName`:** Gerencie a versão do seu aplicativo para controle de lançamentos e atualizações.

   4. **Activity Principal (`app/src/main/java/<pacote>/MainActivity.kt`):**
      - **Pacote:** Certifique-se de que a declaração do pacote corresponda ao seu pacote definido no `build.gradle.kts`.
      - **Tema:** Personalize o tema do seu aplicativo. Você pode renomear a função do tema e modificar as cores, estilos de texto (tipografia) e formas nos arquivos de tema.
      - **Conteúdo:** Modifique o conteúdo da sua `Activity` principal. A função `setContent` é onde você define a interface do usuário usando Compose.

   5. **Testes (`app/src/test/java/<pacote>/` e `app/src/androidTest/java/<pacote>/`):**
      - **Pacote:** As declarações de pacote nos arquivos de teste devem coincidir com o pacote do seu aplicativo.
      - **Atualize os testes:** Adapte os testes de exemplo para refletir as mudanças na sua aplicação.

   6. **Tema da Interface do Usuário (`app/src/main/java/<pacote>/ui/theme/`):**
      - Personalize os arquivos de tema (cores, tipografia, formas) na pasta `ui/theme`.

   7. **Recursos (`app/src/main/res/`):**
      - **`strings.xml`:** Altere o nome do aplicativo e outras strings.
      - **`themes.xml`:** Personalize os temas visuais do seu app.
      - Adicione outros recursos, como imagens ou layouts, conforme necessário.

   8. **Controle de Versão:**
      - Certifique-se de que seu projeto esteja configurado com um sistema de controle de versão como o Git.

   9. **Android Manifest (`app/src/main/AndroidManifest.xml`):**
      - **Tema do aplicativo:** Defina o tema principal do aplicativo no elemento `<application>`.
      - **Tema da activity:** Configure o tema de cada activity individualmente, se necessário.
      - **Permissões:** Declare as permissões necessárias para o seu aplicativo.
      - **Componentes:** Registre outros componentes do aplicativo, como services, broadcast receivers, etc.

5. **Executar o aplicativo:**

   Compile e execute o aplicativo em um emulador ou dispositivo físico.

## Boas práticas

Este template inclui algumas boas práticas para desenvolvimento Android:

* **Jetpack Compose:** UI declarativa moderna.
* **Material Design 3:**  Componentes e estilos visuais atualizados.
* **Arquitetura recomendada:** Implemente uma arquitetura adequada (MVVM, MVI, etc.) à medida que seu projeto evolui.

## Recursos adicionais

* [Documentação do Android](https://developer.android.com/docs)
* [Jetpack Compose](https://developer.android.com/jetpack/compose)
* [Material Design 3](https://m3.material.io/)

## Contribuições

Contribuições são bem-vindas! Sinta-se à vontade para abrir issues ou pull requests.


## Licença

[MIT](LICENSE)
