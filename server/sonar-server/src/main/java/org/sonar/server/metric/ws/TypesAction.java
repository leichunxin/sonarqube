/*
 * SonarQube, open source software quality management tool.
 * Copyright (C) 2008-2014 SonarSource
 * mailto:contact AT sonarsource DOT com
 *
 * SonarQube is free software; you can redistribute it and/or
 * modify it under the terms of the GNU Lesser General Public
 * License as published by the Free Software Foundation; either
 * version 3 of the License, or (at your option) any later version.
 *
 * SonarQube is distributed in the hope that it will be useful,
 * but WITHOUT ANY WARRANTY; without even the implied warranty of
 * MERCHANTABILITY or FITNESS FOR A PARTICULAR PURPOSE.  See the GNU
 * Lesser General Public License for more details.
 *
 * You should have received a copy of the GNU Lesser General Public License
 * along with this program; if not, write to the Free Software Foundation,
 * Inc., 51 Franklin Street, Fifth Floor, Boston, MA  02110-1301, USA.
 */

package org.sonar.server.metric.ws;

import org.sonar.api.measures.Metric;
import org.sonar.api.server.ws.Request;
import org.sonar.api.server.ws.Response;
import org.sonar.api.server.ws.WebService;
import org.sonar.api.utils.text.JsonWriter;

public class TypesAction implements MetricsWsAction {
  @Override
  public void define(WebService.NewController context) {
    context.createAction("types")
      .setDescription("List all available metric types.")
      .setResponseExample(getClass().getResource("example-types.json"))
      .setSince("5.2")
      .setHandler(this);
  }

  @Override
  public void handle(Request request, Response response) throws Exception {
    JsonWriter json = response.newJsonWriter();
    json.beginObject();
    json.name("types");
    json.beginArray();
    for (Metric.ValueType metricType : Metric.ValueType.values()) {
      json.beginObject();
      json.prop("key", metricType.name());
      json.prop("name", metricType.description());
      json.endObject();
    }
    json.endArray();
    json.endObject();
    json.close();
  }
}
