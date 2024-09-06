# Minecraft Enhance Plugin (mc-enhancemc-plugin)

`mc-enhancemc-plugin` is a Minecraft plugin that provides essential commands for and features for a [PaperMC](https://papermc.io/)-based [Minecraft](https://www.minecraft.net/) server

This plugin is designed to simplify gameplay features, enhance base gameplay by providing some quality of life improvements during multiplayer gameplay

## Features

- **Teleport Requests**: Allows players to send, cancel, accept and deny teleport requests
- **Custom Messages**: Informative messages are sent to players when teleport requests are handled

## Commands

| Command              | Aliases                                          | Description                                                             | Usage           |
|----------------------|--------------------------------------------------|-------------------------------------------------------------------------|-----------------|
| `/teleport-request` | - `tpa`<br>- `tpask`<br>- `tpr`<br>- `tprequest` | Sends a teleport request to a specific player                           | `/tpa <player>` |
| `/teleport-cancel`  | - `tpcancel`<br>- `tpacancel`                    | Cancels all sent teleport requests                                      | `/tpacancel`    |
| `/teleport-accept`  | - `tpaccept`                                     | Accepts the most recent teleport request                                | `/tpaccept`     |
| `/teleport-deny`    | - `tpdeny`<br>- `tpadeny`                        | Denies the most recent teleport request and notifies the source player  | `/tpdeny`       |

## Installation

1. Download the latest release of the plugin from the [Releases](https://github.com/slgnalin/mc-enhancemc-plugin/releases) page
2. Place the plugin JAR file in the `plugins` directory of your Minecraft server
3. Restart the server to load the plugin


## Configuration

No additional configuration is required for this plugin

All functionality is provided through the available commands

## Contributing

Contributions are welcome! Feel free to fork the repository and submit a pull request with your changes

For major changes, please open an issue to discuss what you would like to improve

## License
~~~~
See [LICENSE](LICENSE)

## Author

- **slgnalin** - [GitHub](https://github.com/slgnalin)

## Support - GitHub Issues
For any questions or support, please visit the [GitHub Issues](https://github.com/slgnalin/mc-enhancemc-plugin/issues) page
